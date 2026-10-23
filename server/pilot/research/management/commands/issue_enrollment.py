import getpass
from django.core.management.base import BaseCommand, CommandError
from research.services import issue_grant
class Command(BaseCommand):
    help='Issue a one-time 24h research enrollment code after reviewing consent evidence. Never put a name in the reference.'
    def add_arguments(self,parser):
        parser.add_argument('--study',choices=('primary','secondary'),default='primary')
        parser.add_argument('--schema-version',type=int,choices=(1,2),default=1)
    def handle(self,*args,**options):
        reference=getpass.getpass('Approved consent evidence reference (not a name): ')
        try:grant,code=issue_grant(reference,study=options['study'],schema_version=options['schema_version'])
        except Exception:raise CommandError('Could not issue grant; check evidence reference and private database.')
        self.stdout.write('Give this enrollment code privately to the participant. Do not log/store it in tickets:\n'+code)
        self.stdout.write('Grant expires: '+grant.expires_at.isoformat())
