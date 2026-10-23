from datetime import timedelta
from django.core.management.base import BaseCommand,CommandError
from django.utils import timezone
from research.models import Maintenance
class Command(BaseCommand):
    help='Exit nonzero when purge has not run within an hour; output no participant information.'
    def handle(self,*args,**options):
        row=Maintenance.objects.filter(name='purge').first()
        if not row or timezone.now()-row.last_success>timedelta(hours=1):raise CommandError('Retention maintenance overdue')
        self.stdout.write('Retention maintenance current')
