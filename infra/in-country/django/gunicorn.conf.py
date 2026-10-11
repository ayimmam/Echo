# Gateway must terminate HTTPS and strip untrusted forwarded headers.
bind = '127.0.0.1:8001'
workers = 2
threads = 1
timeout = 30
max_requests = 500
max_requests_jitter = 50
accesslog = None  # Provider/proxy logs also require redaction and Ethiopia-only retention.
errorlog = '-'
capture_output = False
