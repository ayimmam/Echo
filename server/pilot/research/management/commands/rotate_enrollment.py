import getpass
from django.core.management.base import BaseCommand,CommandError
from research.services import rotate_enrollment,Rejected
class Command(BaseCommand):
    help='Recover lost enrollment responses/credentials; invalidate old token and summaries without extending consent.'
    def handle(self,*args,**options):
        reference=getpass.getpass('Existing consent reference, after participant requests recovery: ')
        try:grant,code=rotate_enrollment(reference)
        except Rejected:raise CommandError('Grant missing, withdrawn or expired; do not override participant withdrawal.')
        self.stdout.write('Old token and summaries invalidated. Give this code privately:\n'+code)
        self.stdout.write('Original grant expiry remains: '+grant.expires_at.isoformat())
