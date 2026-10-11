from django.core.management.base import BaseCommand
from research.services import purge
class Command(BaseCommand):
    help='Expire summaries, enrollment credentials and temporary rate windows. Safe for repeated cron invocation.'
    def handle(self,*args,**options):self.stdout.write('Retention purge completed; deleted objects: '+str(purge()))
