import json
from datetime import timedelta
from django.db import DatabaseError
from functools import wraps
from django.http import JsonResponse
from django.shortcuts import render
from django.contrib.auth.decorators import login_required, permission_required
from django.contrib.auth.views import LoginView
from django.views.decorators.csrf import csrf_exempt
from django.views.decorators.http import require_GET
from django.utils import timezone
from . import services
from .models import Maintenance

def response(code,status):return JsonResponse({'error':code},status=status)

def json_api(limit=30):
    def decorate(fn):
        @csrf_exempt
        @wraps(fn)
        def wrapped(request):
            if request.method!='POST':return response('method_not_allowed',405)
            if request.content_type!='application/json':return response('json_required',415)
            try:
                length=int(request.META.get('CONTENT_LENGTH','0') or 0)
                if length<=0 or length>4096:return response('body_limit',413)
                if not services.allow_rate('api:'+request.META.get('REMOTE_ADDR','unknown'),limit):return response('rate_limited',429)
                def pairs(values):
                    result={}
                    for k,v in values:
                        if k in result:raise ValueError('duplicate')
                        result[k]=v
                    return result
                payload=json.loads(request.body,object_pairs_hook=pairs,parse_constant=lambda _: (_ for _ in ()).throw(ValueError()))
                if not isinstance(payload,dict):return response('invalid_fields',400)
                return fn(request,payload)
            except services.Rejected as error:return response(error.code,error.status)
            except (ValueError,TypeError,OverflowError,UnicodeError):return response('invalid_json',400)
        return wrapped
    return decorate

def bearer(request):
    header=request.headers.get('Authorization','')
    if not header.startswith('Bearer ') or not 30<=len(header[7:])<=100:raise services.Rejected('unauthorized',401)
    return header[7:]

@json_api(10)
def enroll(request,payload):
    if set(payload)!={'enrollment_code'} or not isinstance(payload['enrollment_code'],str) or not 30<=len(payload['enrollment_code'])<=100:raise services.Rejected('invalid_fields')
    return JsonResponse(services.enroll(payload['enrollment_code']))

@json_api()
def ingest(request,payload):return JsonResponse({'status':services.accept(bearer(request),payload)})

@json_api()
def withdraw(request,payload):
    if payload!={}:raise services.Rejected('invalid_fields')
    return JsonResponse({'status':services.withdraw(bearer(request))})

@require_GET
def health(request):
    # No unauthenticated database statistics or participant information.
    return JsonResponse({'status':'ok','service':'echo-research','schema_version':2})

@require_GET
def ready(request):
    try:
        state=Maintenance.objects.filter(name='purge').first()
        ok=state is not None and timezone.now()-state.last_success<=timedelta(hours=1)
    except DatabaseError:
        ok=False
    return JsonResponse({'status':'ready' if ok else 'not_ready'},status=200 if ok else 503)

@login_required
@permission_required('research.view_research_aggregate',raise_exception=True)
def dashboard(request):
    # Filter parameters would enable unsafe small-cell/repeated-release exploration.
    if request.GET:return response('filters_not_supported',400)
    return render(request,'research/dashboard.html',{'aggregate':services.aggregate(study='primary'), 'study':'Primary',
        'maintenance':Maintenance.objects.filter(name='purge').first()})

@login_required
@permission_required('research.view_secondary_aggregate',raise_exception=True)
def secondary_dashboard(request):
    if request.GET:return response('filters_not_supported',400)
    return render(request,'research/dashboard.html',{'aggregate':services.aggregate(study='secondary'), 'study':'Secondary',
        'maintenance':Maintenance.objects.filter(name='purge').first()})

class LimitedLogin(LoginView):
    template_name='research/login.html'
    def post(self,request,*args,**kwargs):
        if not services.allow_rate('login:'+request.META.get('REMOTE_ADDR','unknown'),5):return response('rate_limited',429)
        return super().post(request,*args,**kwargs)
login=LimitedLogin.as_view()
