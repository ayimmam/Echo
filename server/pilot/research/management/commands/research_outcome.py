import json
from django.core.management.base import BaseCommand
from research.services import usefulness_outcome

class Command(BaseCommand):
    help = 'Print the versioned aggregate card-usefulness outcome. Contains no participant identifiers.'
    def add_arguments(self, parser):
        parser.add_argument('--study', choices=('primary', 'secondary'), default='primary')
    def handle(self, *args, **options):
        self.stdout.write(json.dumps(usefulness_outcome(study=options['study']), sort_keys=True))
