from django.shortcuts import render
from django.http import HttpResponse
import json
from django.views.decorators.csrf import csrf_exempt
from chatbot.chatbot_model_runner import *


# Create your views here.
def index(request):
    return render(request, "home.html")

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


