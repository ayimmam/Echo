import json
import uuid
from datetime import timedelta
from django.test import TestCase,Client
from django.contrib.auth.models import User,Permission
from django.utils import timezone
from .models import Grant,Summary,Maintenance,RateWindow
from . import services

class ResearchTests(TestCase):
    def setUp(self):
        self.now=timezone.now();self.grant,self.code=services.issue_grant('approval-001',self.now)
        self.token=services.enroll(self.code,self.now)['token']
        self.data={'schema_version':1,'record_id':str(uuid.uuid4()),'collected_at_ms':int(self.now.timestamp()*1000),'grade':2,'language':'am','class_size_band':'40-70','lesson_count':2,'recorded_minutes':75,'rating':4,'practice':'understanding','tried':'yes','content_version':'hawassa-coaching-2026-10-draft-1'}
    def post(self,path,data,token=None):
        return self.client.post('/api/v1/research/'+path,json.dumps(data),content_type='application/json',HTTP_AUTHORIZATION='Bearer '+(token or self.token))
    def test_enrollment_one_time(self):
        with self.assertRaises(services.Rejected):services.enroll(self.code,self.now)
        self.assertNotEqual(self.grant.enrollment_hash,self.code)
        self.assertNotEqual(Grant.objects.get(pk=self.grant.pk).token_hash,self.token)
    def test_expired_enrollment(self):
        _,code=services.issue_grant('approved',self.now)
        with self.assertRaises(services.Rejected):services.enroll(code,self.now+timedelta(hours=24))
    def test_identical_retry_and_conflict(self):
        self.assertEqual(self.post('summaries',self.data).json()['status'],'accepted')
        self.assertEqual(self.post('summaries',self.data).json()['status'],'duplicate')
        changed={**self.data,'rating':1}
        self.assertEqual(self.post('summaries',changed).status_code,409)
        self.assertEqual(Summary.objects.count(),1)
    def test_audio_and_notes_forbidden(self):
        for key in ['audio','note','teacher_id','school','transcript']:
            with self.subTest(key=key):self.assertEqual(self.post('summaries',{**self.data,key:'secret'}).status_code,400)
        self.assertEqual(Summary.objects.count(),0)
    def test_malformed_enum_is_not_500(self):
        for key in ['language','practice','class_size_band','tried']:
            self.assertEqual(self.post('summaries',{**self.data,key:{}}).status_code,400)
    def test_boolean_number_rejected(self):self.assertEqual(self.post('summaries',{**self.data,'grade':True}).status_code,400)
    def test_missing_and_unsupported_fields(self):
        self.assertEqual(self.post('summaries',{'schema_version':1}).status_code,400)
        self.assertEqual(self.post('summaries',{**self.data,'schema_version':2}).status_code,400)
    def test_retention_from_collection_not_arrival(self):
        data={**self.data,'collected_at_ms':int((self.now-timedelta(days=29)).timestamp()*1000)}
        services.accept(self.token,data,self.now)
        row=Summary.objects.get();self.assertLess(row.expires_at,self.now+timedelta(days=2))
    def test_expired_future_records_rejected(self):
        self.assertEqual(self.post('summaries',{**self.data,'collected_at_ms':int((self.now-timedelta(days=30,minutes=1)).timestamp()*1000)}).status_code,410)
        self.assertEqual(self.post('summaries',{**self.data,'collected_at_ms':int((self.now+timedelta(hours=1)).timestamp()*1000)}).status_code,400)
    def test_unauthenticated_and_wrong_token(self):
        self.assertEqual(self.client.post('/api/v1/research/summaries',json.dumps(self.data),content_type='application/json').status_code,401)
        self.assertEqual(self.post('summaries',self.data,'x'*43).status_code,401)
    def test_cross_grant_same_record_id_is_isolated(self):
        _,code=services.issue_grant('approval-002',self.now);token=services.enroll(code,self.now)['token']
        services.accept(self.token,self.data,self.now);services.accept(token,{**self.data,'rating':1},self.now)
        self.assertEqual(Summary.objects.count(),2)
    def test_withdrawal_wins_over_late_upload(self):
        self.post('summaries',self.data)
        self.assertEqual(self.post('withdraw',{}).status_code,200)
        self.assertEqual(Summary.objects.count(),0)
        self.assertEqual(self.post('summaries',self.data).status_code,403)
        self.assertEqual(self.post('withdraw',{}).status_code,200)
    def test_expired_grant(self):
        Grant.objects.filter(pk=self.grant.pk).update(expires_at=self.now-timedelta(seconds=1))
        self.assertEqual(self.post('summaries',self.data).status_code,403)
    def test_purge_idempotent(self):
        services.accept(self.token,self.data,self.now)
        services.purge(self.now+timedelta(days=30,seconds=1));services.purge(self.now+timedelta(days=30,seconds=2))
        self.assertEqual(Summary.objects.count(),0);self.assertEqual(Grant.objects.count(),0)
        self.assertEqual(Maintenance.objects.get(name='purge').deleted_count,0)
    def test_read_filters_expired_before_job(self):
        services.accept(self.token,self.data,self.now)
        Summary.objects.update(expires_at=self.now-timedelta(seconds=1))
        self.assertFalse(services.aggregate(self.now)['available'])
    def test_k_min_separate_ratings_and_equal_participant_weight(self):
        for i in range(5):
            _,code=services.issue_grant('ref-'+str(i),self.now);token=services.enroll(code,self.now)['token']
            services.accept(token,{**self.data,'record_id':str(uuid.uuid4()),'rating':None if i==0 else 5},self.now)
        value=services.aggregate(self.now);self.assertTrue(value['available']);self.assertIsNone(value['rating'])
        self.assertNotIn('participants',services.aggregate(self.now+timedelta(days=31)))
    def test_dashboard_permission_not_staff_flag(self):
        user=User.objects.create_user('operator',password='a-test-passphrase',is_staff=True)
        self.client.force_login(user)
        self.assertEqual(self.client.get('/research/').status_code,403)
        user.user_permissions.add(Permission.objects.get(codename='view_research_aggregate'))
        self.assertEqual(self.client.get('/research/').status_code,200)
        self.assertEqual(self.client.get('/research/?language=am').status_code,400)
    def test_dashboard_requires_login(self):self.assertEqual(self.client.get('/research/').status_code,302)
    def test_csrf_required_for_browser_login(self):
        client=Client(enforce_csrf_checks=True)
        self.assertEqual(client.post('/accounts/login/',{'username':'x','password':'y'}).status_code,403)
    def test_duplicate_json_keys_rejected(self):
        r=self.client.post('/api/v1/research/enroll','{"enrollment_code":"a","enrollment_code":"b"}',content_type='application/json')
        self.assertEqual(r.status_code,400)
    def test_limits_and_content_type(self):
        self.assertEqual(self.client.post('/api/v1/research/enroll','x'*4097,content_type='application/json').status_code,413)
        self.assertEqual(self.client.post('/api/v1/research/enroll',{'x':'y'}).status_code,415)
        self.assertEqual(self.client.get('/api/v1/research/summaries').status_code,405)
    def test_rate_limit(self):
        for _ in range(10):self.client.post('/api/v1/research/enroll','{}',content_type='application/json')
        self.assertEqual(self.client.post('/api/v1/research/enroll','{}',content_type='application/json').status_code,429)
    def test_private_headers_and_health(self):
        r=self.client.get('/healthz');self.assertEqual(r.status_code,200)
        self.assertEqual(r['Cache-Control'],'no-store');self.assertEqual(r['X-Frame-Options'],'DENY')
        self.assertNotIn(str(self.grant.id),r.content.decode())
    def test_backup_does_not_contain_collected_data(self):
        import tempfile
        from pathlib import Path
        from django.core.management import call_command
        from io import StringIO
        self.post('summaries',self.data)
        with tempfile.TemporaryDirectory() as folder:
            output=Path(folder)/'access.json'
            call_command('backup_access',str(output),stdout=StringIO())
            contents=output.read_text()
            self.assertNotIn('research.summary',contents);self.assertNotIn('research.grant',contents)
            self.assertNotIn(self.token,contents);self.assertNotIn(self.data['record_id'],contents)
    def test_consent_reference_cannot_inflate_participant_count(self):
        from django.db import IntegrityError,transaction
        with self.assertRaises(IntegrityError),transaction.atomic():services.issue_grant('approval-001',self.now)

    def test_grant_storage_is_bounded(self):
        Summary.objects.bulk_create([Summary(grant=self.grant,record_id=uuid.uuid4(),collected_at=self.now,expires_at=self.now+timedelta(days=1),payload_hash='test',payload=self.data) for _ in range(128)])
        self.assertEqual(self.post('summaries',self.data).status_code,413)
        self.assertEqual(Summary.objects.count(),128)

    def test_readiness_requires_current_retention_maintenance(self):
        self.assertEqual(self.client.get('/readyz').status_code,503)
        services.purge(self.now)
        self.assertEqual(self.client.get('/readyz').status_code,200)
        Maintenance.objects.update(last_success=self.now-timedelta(hours=2))
        self.assertEqual(self.client.get('/readyz').status_code,503)

    def test_rotation_recovers_credentials_without_extending_retention(self):
        services.accept(self.token,self.data,self.now)
        grant,code=services.rotate_enrollment('approval-001',self.now+timedelta(hours=1))
        self.assertEqual(grant.expires_at,self.grant.expires_at)
        self.assertEqual(Summary.objects.count(),0)
        with self.assertRaises(services.Rejected):services.accept(self.token,self.data,self.now)
        replacement=services.enroll(code,self.now+timedelta(hours=1))['token']
        services.accept(replacement,self.data,self.now+timedelta(hours=1))
        self.assertEqual(Grant.objects.count(),1)
        services.withdraw(replacement,self.now+timedelta(hours=1))
        with self.assertRaises(services.Rejected):services.rotate_enrollment('approval-001',self.now+timedelta(hours=2))

from django.test import TransactionTestCase
from django.db import connection,close_old_connections,transaction
from unittest import skipUnless
import threading

@skipUnless(connection.vendor=='postgresql','Row-lock race tests require PostgreSQL')
class PostgresRaceTests(TransactionTestCase):
    def test_withdrawal_serializes_with_inflight_acceptance(self):
        now=timezone.now();grant,code=services.issue_grant('race-ref',now);token=services.enroll(code,now)['token']
        payload={'schema_version':1,'record_id':str(uuid.uuid4()),'collected_at_ms':int(now.timestamp()*1000),'grade':2,'language':'am','class_size_band':'40-70','lesson_count':2,'recorded_minutes':20,'rating':4,'practice':'none','tried':'unknown','content_version':'hawassa-coaching-2026-10-draft-1'}
        locked=threading.Event();started=threading.Event();errors=[]
        def uploader():
            close_old_connections()
            try:
                with transaction.atomic():
                    Grant.objects.select_for_update().get(pk=grant.pk);locked.set()
                    if not started.wait(5):raise AssertionError('withdrawal did not start')
                    services.accept(token,payload,now)
            except BaseException as e:errors.append(e)
            finally:close_old_connections()
        def remover():
            close_old_connections()
            try:
                if not locked.wait(5):raise AssertionError('upload did not lock')
                started.set();services.withdraw(token,now)
            except BaseException as e:errors.append(e)
            finally:close_old_connections()
        a=threading.Thread(target=uploader);b=threading.Thread(target=remover);a.start();b.start();a.join(10);b.join(10)
        self.assertFalse(a.is_alive() or b.is_alive());self.assertEqual(errors,[])
        self.assertEqual(Summary.objects.count(),0)
        with self.assertRaises(services.Rejected):services.accept(token,payload,now)
