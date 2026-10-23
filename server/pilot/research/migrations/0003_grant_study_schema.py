from django.db import migrations, models


class Migration(migrations.Migration):
    dependencies = [('research', '0002_alter_grant_consent_reference')]

    operations = [
        migrations.AlterModelOptions(name='grant', options={'permissions': [
            ('view_research_aggregate', 'View suppressed primary pilot aggregates'),
            ('view_secondary_aggregate', 'View suppressed secondary pilot aggregates')]}),
        migrations.AddField(model_name='grant', name='study', field=models.CharField(default='primary', max_length=16)),
        migrations.AddField(model_name='grant', name='schema_version', field=models.PositiveSmallIntegerField(default=1)),
    ]
