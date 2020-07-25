from django.shortcuts import render
from django.http import HttpResponse
import json
from django.views.decorators.csrf import csrf_exempt

# Create your views here.
def index(request):
    # return HttpResponse("Hello, world. You're at the polls index.")
    return HttpResponse(request, "home.html")

@csrf_exempt
def get_response(request):
    response = {'status': None}

    if request.method == 'POST':
        data = json.loads(request.body)
        message = data['message']
        print(message)

        chat_response = "HI"
        response['message'] = {'text': chat_response, 'user': False, 'chat_bot': True}
        response['status'] = 'ok'

    else:
        response['error'] = 'no post data found'

    return HttpResponse(
        json.dumps(response),
        content_type = "application/json"
    )

def home(request, template_name="home.html"):
    context = {'title': 'TEMP'}
    return render(template_name, context)
