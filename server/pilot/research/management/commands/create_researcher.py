import getpass
from django.contrib.auth import get_user_model,password_validation
from django.contrib.auth.models import Permission
from django.core.exceptions import ValidationError
from django.core.management.base import BaseCommand,CommandError
class Command(BaseCommand):
    help='Create a restricted aggregate viewer, not a staff/superuser account.'
    def add_arguments(self,parser):parser.add_argument('username')
    def handle(self,*args,**options):
        User=get_user_model();name=options['username']
        if User.objects.filter(username=name).exists():raise CommandError('Account already exists')
        password=getpass.getpass('Private research password: ')
        if password!=getpass.getpass('Repeat password: '):raise CommandError('Passwords do not match')
        user=User(username=name)
        try:password_validation.validate_password(password,user)
        except ValidationError:raise CommandError('Password does not meet configured policy')
        user.set_password(password);user.save()
        user.user_permissions.add(Permission.objects.get(codename='view_research_aggregate',content_type__app_label='research'))
        self.stdout.write('Restricted research viewer created.')
