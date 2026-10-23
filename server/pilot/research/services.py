"""All intake and withdrawal use a grant row lock; no teacher IDs trusted from payload."""
import hashlib
import json
import secrets
import uuid
from datetime import datetime, timedelta, timezone as dt_timezone
from django.db import transaction
from django.utils import timezone
from .models import Grant, Summary, RateWindow, Maintenance
RETENTION = timedelta(days=30)
FIELDS = {'schema_version','record_id','collected_at_ms','grade','language','class_size_band','lesson_count','recorded_minutes','rating','practice','tried','content_version'}
FIELDS_V2 = FIELDS - {'tried'} | {'follow_up', 'reflection_completed'}
CONTENT_VERSIONS = {'hawassa-coaching-2026-10-draft-1','hawassa-coaching-2026-10-draft-2'}
PRACTICES_V1 = {'none','understanding','reading','explanations'}
PRACTICES_V2 = PRACTICES_V1 | {'math_reasoning'}

def digest(value): return hashlib.sha256(value.encode()).hexdigest()

class Rejected(Exception):
    def __init__(self, code, status=400): self.code, self.status = code, status

def issue_grant(reference, now=None, *, study='primary', schema_version=1):
    now = now or timezone.now()
    if not reference or len(reference)>100: raise Rejected('invalid_consent_reference')
    if study not in {'primary','secondary'} or schema_version not in {1,2} or (study=='secondary' and schema_version!=2):
        raise Rejected('invalid_study_scope')
    code = secrets.token_urlsafe(32)
    grant = Grant.objects.create(consent_reference=reference, study=study, schema_version=schema_version,
        created_at=now, expires_at=now+RETENTION, enrollment_hash=digest(code), enrollment_expires_at=now+timedelta(hours=24))
    return grant, code

@transaction.atomic
def enroll(code, now=None):
    now = now or timezone.now()
    grant = Grant.objects.select_for_update().filter(enrollment_hash=digest(code)).first()
    if not grant or grant.withdrawn_at or grant.enrolled_at or now>=grant.enrollment_expires_at or now>=grant.expires_at:
        raise Rejected('invalid_enrollment',403)
    token = secrets.token_urlsafe(32)
    grant.token_hash=digest(token); grant.enrolled_at=now
    grant.save(update_fields=['token_hash','enrolled_at'])
    return {'token':token, 'grant_id':str(grant.id), 'expires_at_ms':int(grant.expires_at.timestamp()*1000),
            'study':grant.study, 'schema_version':grant.schema_version}

def locked_grant(token):
    grant=Grant.objects.select_for_update().filter(token_hash=digest(token)).first()
    if not grant: raise Rejected('unauthorized',401)
    return grant

def validate(payload, now, grant=None):
    if not isinstance(payload,dict): raise Rejected('invalid_fields')
    version = payload.get('schema_version')
    if type(version) is not int or version not in {1,2} or set(payload)!=(FIELDS if version==1 else FIELDS_V2):
        raise Rejected('invalid_fields')
    if grant and (version!=grant.schema_version or
                  (grant.study=='primary' and payload['grade'] not in (1,2,3,4)) or
                  (grant.study=='secondary' and payload['grade'] not in (11,12))):
        raise Rejected('study_scope_mismatch')
    def integer(key,lo,hi):
        v=payload[key]
        if type(v) is not int or not lo<=v<=hi: raise Rejected('invalid_'+key)
    integer('schema_version',version,version)
    integer('grade',1,4 if version==1 else 12)
    if version==2 and payload['grade'] not in {1,2,3,4,11,12}: raise Rejected('invalid_grade')
    integer('lesson_count',1 if version==1 else 0,100)
    integer('recorded_minutes',1 if version==1 else 0,12000); integer('collected_at_ms',1,2**53)
    if version==2:
        if type(payload['reflection_completed']) is not bool or not isinstance(payload['follow_up'],str) or payload['follow_up'] not in {'unknown','yes','not_yet','did_not_fit'}:
            raise Rejected('invalid_reflection')
        if (payload['lesson_count']==0)!=(payload['recorded_minutes']==0): raise Rejected('invalid_capture')
    if payload['rating'] is not None: integer('rating',1,5)
    if not isinstance(payload['language'],str) or not isinstance(payload['class_size_band'],str) or payload['language'] not in {'am','en','om','sid'} or payload['class_size_band'] not in {'1-39','40-70','71-150'}: raise Rejected('invalid_context')
    if not isinstance(payload['practice'],str) or payload['practice'] not in (PRACTICES_V1 if version==1 else PRACTICES_V2): raise Rejected('invalid_reflection')
    if version==1 and (not isinstance(payload['tried'],str) or payload['tried'] not in {'unknown','yes','no'}): raise Rejected('invalid_reflection')
    if not isinstance(payload['content_version'],str) or payload['content_version'] not in CONTENT_VERSIONS: raise Rejected('unsupported_content')
    if payload['practice']=='reading' and payload['grade'] not in {1,2,3,4}: raise Rejected('invalid_practice_scope')
    if payload['practice']=='math_reasoning' and (payload['grade'] not in {11,12} or
                                                 payload['content_version']!='hawassa-coaching-2026-10-draft-2'):
        raise Rejected('invalid_practice_scope')
    if version==2 and payload['grade'] in {11,12} and payload['content_version']!='hawassa-coaching-2026-10-draft-2':
        raise Rejected('unsupported_content')
    try: record_id=uuid.UUID(payload['record_id'])
    except (ValueError,TypeError,AttributeError): raise Rejected('invalid_record_id')
    if str(record_id)!=payload['record_id']: raise Rejected('invalid_record_id')
    collected=datetime.fromtimestamp(payload['collected_at_ms']/1000,dt_timezone.utc)
    if collected>now+timedelta(minutes=5): raise Rejected('future_record')
    if now>=collected+RETENTION: raise Rejected('expired_record',410)
    return record_id,collected

@transaction.atomic
def accept(token,payload,now=None):
    now=now or timezone.now()
    grant=locked_grant(token)
    if grant.withdrawn_at or now>=grant.expires_at: raise Rejected('grant_inactive',403)
    record_id,collected=validate(payload,now,grant)
    fingerprint=digest(json.dumps(payload,sort_keys=True,separators=(',',':')))
    previous=Summary.objects.filter(grant=grant,record_id=record_id).first()
    if previous:
        if previous.payload_hash!=fingerprint:raise Rejected('record_conflict',409)
        return 'duplicate'
    if Summary.objects.filter(grant=grant).count()>=128:raise Rejected('grant_record_limit',413)
    Summary.objects.create(grant=grant,record_id=record_id,collected_at=collected,expires_at=min(collected+RETENTION,grant.expires_at),payload_hash=fingerprint,payload=payload)
    return 'accepted'

@transaction.atomic
def withdraw(token,now=None):
    now=now or timezone.now();grant=locked_grant(token)
    Summary.objects.filter(grant=grant).delete()
    if not grant.withdrawn_at:
        grant.withdrawn_at=now;grant.save(update_fields=['withdrawn_at'])
    return 'withdrawn'

@transaction.atomic
def allow_rate(key,limit,now=None):
    now=now or timezone.now(); hashed=digest(key)
    row,_=RateWindow.objects.get_or_create(key=hashed,defaults={'started_at':now,'hits':0})
    row=RateWindow.objects.select_for_update().get(pk=hashed)
    if now-row.started_at>=timedelta(minutes=1):row.started_at=now;row.hits=0
    if row.hits>=limit:return False
    row.hits+=1;row.save();return True

@transaction.atomic
def purge(now=None):
    now=now or timezone.now()
    count,_=Summary.objects.filter(expires_at__lte=now).delete()
    # Expired grant removal also removes its token, consent reference and any late summary.
    extra,_=Grant.objects.filter(expires_at__lte=now).delete();count+=extra
    RateWindow.objects.filter(started_at__lt=now-timedelta(days=1)).delete()
    Maintenance.objects.update_or_create(name='purge',defaults={'last_success':now,'deleted_count':count})
    return count

def aggregate(now=None, *, study='primary'):
    now=now or timezone.now()
    # No filtering/drill-down: one combined pilot cohort, per-participant averages first.
    rows=Summary.objects.filter(expires_at__gt=now,grant__withdrawn_at__isnull=True,grant__expires_at__gt=now,
                                grant__study=study)
    by_grant={}
    for row in rows.iterator():by_grant.setdefault(row.grant_id,[]).append(row.payload)
    if len(by_grant)<5:return {'available':False,'reason':'At least five contributing participants are required.'}
    means=[]
    for values in by_grant.values():
        ratings=[v['rating'] for v in values if v['rating'] is not None]
        if ratings: means.append(sum(ratings)/len(ratings))
    # Separate contributor gate for ratings; no zeros or subgroup counts for suppressed fields.
    return {'available':True,'participants':len(by_grant),'rating':round(sum(means)/len(means),2) if len(means)>=5 else None}

@transaction.atomic
def rotate_enrollment(reference,now=None):
    """Private operator recovery; keep one grant/participant and original retention deadline."""
    now=now or timezone.now()
    grant=Grant.objects.select_for_update().filter(consent_reference=reference).first()
    if not grant or grant.withdrawn_at or now>=grant.expires_at:raise Rejected('grant_inactive',403)
    Summary.objects.filter(grant=grant).delete()
    code=secrets.token_urlsafe(32)
    grant.token_hash=None;grant.enrolled_at=None;grant.enrollment_hash=digest(code)
    grant.enrollment_expires_at=min(grant.expires_at,now+timedelta(hours=24))
    grant.save(update_fields=['token_hash','enrolled_at','enrollment_hash','enrollment_expires_at'])
    return grant,code
