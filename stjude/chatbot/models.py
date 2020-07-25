from django.db import models

# Create your models here.
class chatmessage(models.Model):
    name = models.CharField(max_length=100)
    date = models.DateTimeField('date published')