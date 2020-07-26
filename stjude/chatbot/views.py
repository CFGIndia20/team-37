from django.shortcuts import render, redirect
from django.http import HttpResponse
import json
from django.views.decorators.csrf import csrf_exempt
from chatbot.chatbot_model_runner import *
from datetime import date
from .forms import LoginForm, OptionForm, PatientForm,CheckoutForm

import pyrebase
#Session Variable
session_username = ''

firebaseConfig = {
  "apiKey": "AIzaSyD_P9hUWKKABqcaZZlh2pDtQH3UxrXvoxY",
  "authDomain": "feedbackcfg.firebaseapp.com",
  "databaseURL": "https://feedbackcfg.firebaseio.com",
  "projectId": "feedbackcfg",
  "storageBucket": "feedbackcfg.appspot.com",
  "messagingSenderId": "164734184950",
  "appId": "1:164734184950:web:b7ec29cbd00b5cf16fabd7",
  "measurementId": "G-4E80TEEZCC"
}

firebase = pyrebase.initialize_app(firebaseConfig)
authe = firebase.auth()
database = firebase.database()
db = firebase.database()


#Home
def index(request):
    if(session_username != ''):
        return render(request, 'index.html', {'username': session_username})
    return render(request,'index.html',{'username':session_username})

def chatbot(request):
    return render(request, "home.html")

@csrf_exempt
def get_response(request):
    response = {'status': None}

    if request.method == 'POST':
        data = json.loads(request.body)
        message = data['message']
        # print(message)

        chat_response = chatbot_response(message)
        patient_count = chatbot_response("patient count")
        # print(chatbot_response)
        # print(chat_response)
        if chatbot_response==patient_count:
            print("AAAA")
            chat_response=str(return_patient_count())
        elif chatbot_response=="patient_phone_number":
            chat_response=return_patient_phone(request)
        response['message'] = {'text': chat_response, 'user': False, 'chat_bot': True}
        response['status'] = 'ok'

    else:
        response['error'] = 'no post data found'

    return HttpResponse(
        json.dumps(response),
        content_type = "application/json"
    )

def return_patient_count():

    patients=db.child("patients").get().val()
    print(patients)
    count=0
    for i in patients:
        count+=1
    return count
#patient
def return_patient_phone(request):
    found=False
    patientid=-1
    for word in request.split():
        if word.isdigit():
            if found==False:
                patientid=word
            else:
                return "please ask again with a valid patient id"
    if patientid==-1:
        return "please ask again with a patient id"
    patients=db.child("patients").get().val()
    for i in patients:
        if patientid==db.child("patients").child(i).child('id').get().val():
            return str(db.child("patients").child(i).child('phone').get().val())
    return "patient id not found in records"
# def get_response(request):
#     response = {'status': None}

#     if request.method == 'POST':
#         data = json.loads(request.body)
#         message = data['message']
#         print(message)

#         chat_response = chatbot_response(message)
#         response['message'] = {'text': chat_response, 'user': False, 'chat_bot': True}
#         response['status'] = 'ok'

#     else:
#         response['error'] = 'no post data found'

#     return HttpResponse(
#         json.dumps(response),
#         content_type = "application/json"
#     )

#Login Form
def login(request):
    if request.method == "POST":
        login_data = LoginForm(request.POST)
        username = login_data.data['login']
        password = login_data.data['password']

        print(username,password)

        admins = db.child('admin').get().val()
        admins = dict(admins)
        for k,adm in admins.items():

            print(adm)

            if str(adm["username"]) == username and str(adm["password"]) == password:
                # global session_username
                # session_username = username
                return render(request, 'loggedin.html')

        donor = db.child('donor').get().val()
        donor = dict(donor)
        for k,don in donor.items():
            if str(don["username"]) == username and str(don["password"]) == password:
                # global session_username
                # session_username = username
                # return render(request, 'admin-options.html')
                return HttpResponse("<h1>HI</h1>")
        
        return HttpResponse("<h1>Error<h1>")
        # global session_username
        # session_username = username
        # return redirect(to='http://127.0.0.1:8000/admin-option')
    else:
        return render(request, 'login.html')

#pending part has view-patient and urls.py updated.
def view_patient(request):
    #error part
    pat = PatientForm(request.POST)
    patient_id = pat.data['patient_id']
    patients=db.child('patients').get().val()
    patients = dict(patients)
    required_patient_details={}
    for k,patient in patients.items():
        if patient["id"]==int(patient_id):
            required_patient_details['phone']=patient["phone"]
            required_patient_details['id']=patient["id"]
            required_patient_details['enter_date']=patient["enterdate"]
            required_patient_details['exit_date']=patient["exitdate"]
            required_patient_details['name']=patient["name"]
            break
    
    print(required_patient_details)

    return render(request, 'view_details.html',{'patient_details': required_patient_details})

def checkout(request):
    #error part
    pat = CheckoutForm(request.POST)
    patient_id = pat.data['patient_id']
    patients_ref=db.child('patients').get().val()
    patients_ref = dict(patients_ref)
    center_id=-1
    for patient in patients_ref:
        print(patient)
        if(patient["id"]==patient_id):
            db.child('patients').update({patient+'/exitdate':date.today()})
            center_id=patient['center_id']
            break
    center_ref=db.child('centers').get().val()
    for cen in center_ref:
        if db.child("centers").child("center_id")==center_id:
            for unit in db.child("centers").child("unit"):
                if(unit==patient_id):
                    cen.update({'unit':'A'})
    return render(request, 'checkout_final')


def optionChosen(request):
    if request.method == "POST":
        request.method = "GET"
        opt_data = OptionForm(request.POST)
        opt = opt_data.data['opt']
        
        center_id = -1
        admins = db.child('admin').get().val()
        admins = dict(admins)
        print(admins)
        for k,adm in admins.items():
            print(adm)
            if adm["admin_id"] == 1:
                center_id = adm["center_id"]
        print(center_id)
        centers = db.child('centers').get().val()
        cent = -1
        for k,cen in centers.items():
            if cen["center_id"] == center_id:
                cent = cen
                break
        print(cent)

        
        if opt == "add":
            unit = cent["unit"]
            temp = {}
            for k,v in enumerate(unit):
                temp[k] = v
            unit = temp
            return render(request, 'available.html',{'units' : unit})
        elif opt == "checkout":
            return render(request, 'checkout-patient.html')
        elif opt == "view":
            return render(request,'view-patient.html')
    else:
        return render(request, 'admin-options.html')

# def loginPage(request):
#     return render(request, 'login.html')