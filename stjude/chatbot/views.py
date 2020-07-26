from django.shortcuts import render, redirect
from django.http import HttpResponse
import json
from django.views.decorators.csrf import csrf_exempt
# from chatbot.chatbot_model_runner import *
from datetime import date
from .forms import LoginForm, OptionForm

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

@csrf_exempt
def get_response(request):
    response = {'status': None}

    if request.method == 'POST':
        data = json.loads(request.body)
        message = data['message']
        # print(message)

        chat_response = chatbot_response(message)
        response['message'] = {'text': chat_response, 'user': False, 'chat_bot': True}
        response['status'] = 'ok'

    else:
        response['error'] = 'no post data found'

    return HttpResponse(
        json.dumps(response),
        content_type = "application/json"
    )

#Login Form
def login(request):
    try:
        login_data = LoginForm(request.POST)
        username = login_data.data['username']
        password = login_data.data['password']
        # user = get_object_or_404(User, username = username, password = password)
        print(username)
        global session_username
        session_username = username
        # return redirect(to='http://127.0.0.1:8000/')
        return redirect(to='http://127.0.0.1:8000/admin-option')
    except:
        return render(request, 'e403.html')

#pending part has view-patient and urls.py updated.
def view_patient(request):
    #error part
    patient_id=request['patient_id']
    patients=db.child('patients').get().val()
    required_patient_details={}
    for k,patient in patients.items():
        if patient["patient_id"]==request["patientid"]:
            required_patient_details['phone']=patient["phone"]
            required_patient_details['id']=patient["id"]
            required_patient_details['enter_date']=patient["enterdate"]
            required_patient_details['exit_date']=patient["exitdate"]
            required_patient_details['name']=patient["name"]
            break                  
    return render(request, 'view_details',{'patient_details': required_patient_details})
def checkout(request):
    #error part
    patient_id=request['patient_id']
    patients_ref=db.child('patients').child('patient_id')
    patients_ref.set({'exitdate':date.today()})
    center_id=patients_ref.child('center_id')
    center_ref=db.child('centers')
    for cen in center_ref:
        if cen.child("center_id")==centre_id:
            for unit in cen.child("unit"):
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
        # for adm in admins:
        #     print(adm)
        #     temp = db.child('admin').child(adm)
        #     if temp.child('admin_id').get().val() == 1:
        #         center_id = temp.child('center_id').get().val()
        #         break
        # print(center_id)
        # centers = db.child('centers').get().val()
        # center_ref = -1
        # for cen in centers:
        #     center_ref = db.child('centers').child(cen)
        #     if center_ref.child('center_id').get().val() == center_id:
        #         break
        if opt == "add":
            unit = cent["unit"]
            temp = {}
            for k,v in enumerate(unit):
                temp[k] = v
            unit = temp
            return render(request, 'add-patient.html',{'units' : unit})
        
        elif opt == "checkout":
            return render(request, 'checkout-patient.html')
        elif opt == "view":
            return render(request,'view-patient.html')
    else:
        return render(request, 'admin-options.html')




