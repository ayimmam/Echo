import os
from pathlib import Path
from django.core.management import call_command
from django.core.management.base import BaseCommand,CommandError
class Command(BaseCommand):
    help='Back up research access accounts only. NEVER includes grants, summaries or sessions.'
    def add_arguments(self,parser):parser.add_argument('output')
    def handle(self,*args,**options):
        path=Path(options['output'])
        if not path.is_absolute():raise CommandError('Choose an absolute path outside the public document root')
        try:
            fd=os.open(path,os.O_WRONLY|os.O_CREAT|os.O_EXCL,0o600)
            with os.fdopen(fd,'w') as stream:call_command('dumpdata','auth','contenttypes',stdout=stream)
        except FileExistsError:raise CommandError('Refusing to overwrite an existing backup')
        self.stdout.write('Access-only backup created. Encrypt and store in Ethiopia; never use full-database snapshots without an approved expiry/deletion mechanism.')
