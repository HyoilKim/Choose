from django.urls import path, include
from search import views
from rest_framework.urlpatterns import format_suffix_patterns

from django.views.decorators.csrf import csrf_exempt

urlpatterns = [
    path('enroll-user/<user_name>/<password>/<birthday>', views.EnrollUser.as_view()),
    path('get-user/', views.UserList.as_view()),
    path('get-user/<user_name>', views.OneUser.as_view()),
    path('get-item/<str:category>', views.CategoryItems.as_view()),
    # path('get-item/<user_name>/<str:category>', views.UserCategoryItems.as_view()),
    path('get-item/search/<str:keyword>', views.SearchKeyword.as_view()),
    path('get-item/item/<int:id>', views.OneItem.as_view()),
    path('get-cart/<user_name>', views.UserCart.as_view()),
    path('get-cart/<user_name>/<item_id>/<number>', views.UpdateCartItem.as_view()),
    path('add-cart/<user_name>/<item_id>', views.AddCartItem.as_view()),
    path('get-like/<user_name>', views.UserLike.as_view()),
    path('add-like/<user_name>/<item_id>', views.AddLikeItem.as_view()),
    path('get-recent/<user_name>', views.UserRecent.as_view()),
    path('get-recent/<user_name>/<item_id>', views.AddRecentLookUp.as_view()),
    path('get-card/<user_name>/<card_num>/<card_name>/<validate_num>/<cvc>', views.AddUserCardInfo.as_view())
]

# urlpatterns = format_suffix_patterns(urlpatterns)