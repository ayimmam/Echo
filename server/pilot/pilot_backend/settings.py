"""Provider-independent settings. Production refuses unverified residency/retention."""
import os
from pathlib import Path
from django.core.exceptions import ImproperlyConfigured
BASE_DIR = Path(__file__).resolve().parent.parent
ENV = os.environ.get('ECHO_ENV', 'development')
if ENV not in {'development', 'production'}:
    raise ImproperlyConfigured('Unknown ECHO_ENV')
PRODUCTION = ENV == 'production'
SECRET_KEY = os.environ.get('ECHO_SECRET_KEY', '')
if PRODUCTION:
    if len(SECRET_KEY) < 50:
        raise ImproperlyConfigured('A private ECHO_SECRET_KEY of at least 50 characters is required')
    # Deployment must never guess residency or provider capability from the hostname.
    if os.environ.get('ECHO_HOSTING_APPROVED') != 'yes' or os.environ.get('ECHO_RETENTION_APPROVED') != 'yes':
        raise ImproperlyConfigured('Hosting and backup/deletion evidence must be approved before production')
else:
    SECRET_KEY = SECRET_KEY or 'development-only-no-real-data-echo-pilot-key'
DEBUG = False
ALLOWED_HOSTS = [h.strip() for h in os.environ.get('ECHO_ALLOWED_HOSTS', 'localhost,127.0.0.1,testserver' if not PRODUCTION else '').split(',') if h.strip()]
if not ALLOWED_HOSTS or '*' in ALLOWED_HOSTS:
    raise ImproperlyConfigured('Exact allowed hosts required')
INSTALLED_APPS = ['django.contrib.auth', 'django.contrib.contenttypes', 'django.contrib.sessions', 'django.contrib.staticfiles', 'research']
MIDDLEWARE = ['django.middleware.security.SecurityMiddleware', 'django.contrib.sessions.middleware.SessionMiddleware', 'django.middleware.common.CommonMiddleware', 'django.middleware.csrf.CsrfViewMiddleware', 'django.contrib.auth.middleware.AuthenticationMiddleware', 'django.middleware.clickjacking.XFrameOptionsMiddleware', 'research.middleware.PrivateResponses']
ROOT_URLCONF = 'pilot_backend.urls'
WSGI_APPLICATION = 'pilot_backend.wsgi.application'
ASGI_APPLICATION = 'pilot_backend.asgi.application'
TEMPLATES = [{'BACKEND':'django.template.backends.django.DjangoTemplates','DIRS':[], 'APP_DIRS':True, 'OPTIONS': {'context_processors':['django.template.context_processors.request','django.contrib.auth.context_processors.auth']}}]
if os.environ.get('ECHO_DB_ENGINE', 'postgresql' if PRODUCTION else 'sqlite') == 'postgresql':
    DATABASES = {'default': {'ENGINE':'django.db.backends.postgresql','NAME':os.environ.get('ECHO_DB_NAME','echo'), 'USER':os.environ.get('ECHO_DB_USER','echo'), 'PASSWORD':os.environ.get('ECHO_DB_PASSWORD',''), 'HOST':os.environ.get('ECHO_DB_HOST','localhost'), 'PORT':os.environ.get('ECHO_DB_PORT','5432'), 'CONN_MAX_AGE':0, 'OPTIONS': {'sslmode':os.environ.get('ECHO_DB_SSLMODE','verify-full')}}}
else:
    if PRODUCTION: raise ImproperlyConfigured('Production requires PostgreSQL; SQLite is disposable development only')
    DATABASES = {'default': {'ENGINE':'django.db.backends.sqlite3','NAME':os.environ.get('ECHO_SQLITE_PATH', str(BASE_DIR / 'development.sqlite3'))}}
AUTH_PASSWORD_VALIDATORS = [{'NAME':'django.contrib.auth.password_validation.UserAttributeSimilarityValidator'}, {'NAME':'django.contrib.auth.password_validation.MinimumLengthValidator','OPTIONS':{'min_length':12}}, {'NAME':'django.contrib.auth.password_validation.CommonPasswordValidator'}, {'NAME':'django.contrib.auth.password_validation.NumericPasswordValidator'}]
TIME_ZONE = 'Africa/Addis_Ababa'
USE_TZ = True
DEFAULT_AUTO_FIELD = 'django.db.models.BigAutoField'
STATIC_URL = '/static/'
STATIC_ROOT = os.environ.get('ECHO_STATIC_ROOT', str(BASE_DIR / 'staticfiles'))
SECURE_SSL_REDIRECT = PRODUCTION
# Set only when the deployment's gateway strips incoming forwarded headers.
SECURE_PROXY_SSL_HEADER = ('HTTP_X_FORWARDED_PROTO','https') if os.environ.get('ECHO_TRUSTED_PROXY') == 'yes' else None
SECURE_HSTS_SECONDS = 31536000 if PRODUCTION else 0
SECURE_HSTS_INCLUDE_SUBDOMAINS = os.environ.get('ECHO_HSTS_INCLUDE_SUBDOMAINS') == 'yes'
SECURE_HSTS_PRELOAD = os.environ.get('ECHO_HSTS_PRELOAD') == 'yes'
SESSION_COOKIE_SECURE = PRODUCTION
CSRF_COOKIE_SECURE = PRODUCTION
SESSION_COOKIE_HTTPONLY = True
SESSION_COOKIE_SAMESITE = 'Strict'
SESSION_COOKIE_AGE = 1800
SESSION_SAVE_EVERY_REQUEST = False
SECURE_CONTENT_TYPE_NOSNIFF = True
DATA_UPLOAD_MAX_MEMORY_SIZE = 4096
FILE_UPLOAD_MAX_MEMORY_SIZE = 0
LOGIN_URL = '/accounts/login/'
LOGIN_REDIRECT_URL = '/research/'
LOGOUT_REDIRECT_URL = '/accounts/login/'
# No request bodies, authentication headers or token values in application logs.
LOGGING = {'version':1,'disable_existing_loggers':False,'handlers':{'null':{'class':'logging.NullHandler'}},'loggers':{'django.request':{'handlers':['null'],'propagate':False},'django.server':{'handlers':['null'],'propagate':False}}}
