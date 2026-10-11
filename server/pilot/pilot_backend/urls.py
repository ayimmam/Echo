from django.urls import path
from django.contrib.auth import views as auth
from research import views
urlpatterns = [path('healthz',views.health), path('readyz',views.ready), path('api/v1/research/enroll',views.enroll), path('api/v1/research/summaries',views.ingest), path('api/v1/research/withdraw',views.withdraw), path('research/',views.dashboard), path('accounts/login/',views.login), path('accounts/logout/',auth.LogoutView.as_view())]
