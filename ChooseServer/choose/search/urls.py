from django.urls import path, include
from search import views
from rest_framework.urlpatterns import format_suffix_patterns

from django.views.decorators.csrf import csrf_exempt

urlpatterns = [
    path('enroll-user/<user_name>/<password>/<birthday>', views.EnrollUser.as_view()),
    path('get-user/', views.UserList.as_view()),
    path('get-user/<user_name>', views.OneUser.as_view()),
    path('get-item/<category>', views.CategoryItems.as_view()),
    path('get-item/<category>/<int:id>', views.OneItem.as_view()),
    path('get-cart/<user_name>', views.UserCart.as_view()),
    path('add-cart/<user_name>/<item_id>', views.AddCartItem.as_view()),
    path('get-like/<user_name>', views.UserLike.as_view()),
    path('add-like/<user_name>/<item_id>', views.AddLikeItem.as_view())
]

# urlpatterns = format_suffix_patterns(urlpatterns)