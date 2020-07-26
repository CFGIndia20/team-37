from django import forms

class LoginForm(forms.Form):
   user = forms.CharField(max_length = 20)
   password = forms.CharField(widget = forms.PasswordInput())

class OptionForm(forms.Form):
    option = forms.CharField(max_length=20)

class PatientForm(forms.Form):
    patient_id = forms.CharField(max_length=20)
class CheckoutForm(forms.Form):
    patient_id=forms.CharField(max_length=30)