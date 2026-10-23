import uuid
from django.db import models

class Grant(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    # Reference to approved consent evidence, never a participant name or school roster.
    consent_reference = models.CharField(max_length=100, unique=True)
    study = models.CharField(max_length=16, default='primary')
    schema_version = models.PositiveSmallIntegerField(default=1)
    created_at = models.DateTimeField()
    expires_at = models.DateTimeField()
    withdrawn_at = models.DateTimeField(null=True)
    enrollment_hash = models.CharField(max_length=64, unique=True)
    enrollment_expires_at = models.DateTimeField()
    enrolled_at = models.DateTimeField(null=True)
    token_hash = models.CharField(max_length=64, unique=True, null=True)
    class Meta:
        permissions = [('view_research_aggregate', 'View suppressed primary pilot aggregates'),
                       ('view_secondary_aggregate', 'View suppressed secondary pilot aggregates')]

class Summary(models.Model):
    grant = models.ForeignKey(Grant, on_delete=models.CASCADE)
    record_id = models.UUIDField()
    collected_at = models.DateTimeField()
    expires_at = models.DateTimeField(db_index=True)
    payload_hash = models.CharField(max_length=64)
    # Strictly validated numeric/enumerated payload; free text/audio are forbidden.
    payload = models.JSONField()
    class Meta:
        constraints = [models.UniqueConstraint(fields=['grant','record_id'],name='unique_grant_record')]

class RateWindow(models.Model):
    key = models.CharField(max_length=64, primary_key=True)
    started_at = models.DateTimeField()
    hits = models.PositiveIntegerField(default=0)

class Maintenance(models.Model):
    name = models.CharField(max_length=32, primary_key=True)
    last_success = models.DateTimeField()
    deleted_count = models.PositiveIntegerField(default=0)
