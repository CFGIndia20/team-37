from django.urls import path

from . import views

urlpatterns = [
    path('', views.index, name='index'),
    path('login/', views.login),
    path('chatbot/', views.chatbot),
    path('option-chosen/', views.optionChosen),
    path('admin-options/', views.optionChosen),
    path('get-response/', views.get_response, name='get_response'),
    path('view-patient/',views.view_patient),
    # path('view-details/',views.view_patient),
    path('checkout-patient/',views.checkout)
]