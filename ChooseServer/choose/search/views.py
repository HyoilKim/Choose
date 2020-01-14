from django.shortcuts import render
from search.serializers import UserSerializer, ItemSerializer, CartSerializer, LikeSerializer, RecentLookUpSerializer
from search.models import User, Item, Cart, Like, RecentLookUp, UserCard

from rest_framework.views import APIView
from rest_framework.response import Response

from django.http import HttpResponse, JsonResponse

# Create your views here.

class UserList(APIView):
    def get(self, request):
        try:
            users = User.objects.all()
            users_serializer = UserSerializer(users, many = True)
            return Response(users_serializer.data)
        except:
            return Response(None)

class OneUser(APIView):
    def get(self, request, user_name):
        try:
            user = User.objects.get(email=user_name)
            user_serializer = UserSerializer(user)
            return Response(user_serializer.data)
        except:
            return Response(None)

class EnrollUser(APIView):
    def put(self, request, user_name, password, birthday):
        try:
            new_user = User(email=user_name, password=password, birthday=birthday)
            new_user.save()

            get_new_user = User.objects.get(email=user_name)
            new_user_serializer = UserSerializer(get_new_user)
            return Response(new_user_serializer.data)
        except:
            return Response("Fail")

class CategoryItems(APIView):
    def get(self, request, category):
        try:
            items = Item.objects.filter(category=category)
            items_serializer = ItemSerializer(items, many=True)
            return Response(items_serializer.data)
        except:
            return Response(None)


class OneItem(APIView):
    def get(self, request, id):
        try:
            item = Item.objects.get(id=id)
            item_serializer = ItemSerializer(item)
            print(item)
            return Response(item_serializer.data)
        except:
            return Response(None)

class UserCart(APIView):
    def get(self, request, user_name):
        user = User.objects.get(email=user_name)
        try:
            user_cart = Cart.objects.filter(user=user.id)

            cart_serializer = CartSerializer(user_cart, many=True)
            # return JsonResponse(new_json_array)
            return Response(cart_serializer.data)
        except:
            return Response(None)

class UpdateCartItem(APIView):
    def put(self, request, user_name, item_id, number):
        user = User.objects.get(email=user_name)
        try:
            user_cart = Cart.objects.filter(user=user.id, item=item_id)
            for item_in_cart in user_cart:
                if item_in_cart.item.id == int(item_id):
                    item_in_cart.count = int(number)
                    item_in_cart.save()
            return Response(None)
        except Exception as e:
            print(e)
            return Response(None)

class AddCartItem(APIView):
    def put(self, request, user_name, item_id):
        user = User.objects.get(email=user_name)
        try:
            user_cart_item = Cart.objects.get(user=user.id, item=item_id)
            user_cart_item.count += 1
            user_cart_item.save()

            cart = Cart.objects.filter(user=user.id)
            cart_serializer = CartSerializer(cart, many=True)
            return Response(len(cart_serializer.data))
        except:
            item = Item.objects.get(id=item_id)
            new_user_cart_item = Cart(user=user, item=item, count= 1)
            new_user_cart_item.save()

            cart = Cart.objects.filter(user=user.id)
            cart_serializer = CartSerializer(cart, many=True)
            return Response(len(cart_serializer.data))

    def delete(self, request, user_name, item_id):
        user = User.objects.get(email=user_name)
        try:
            user_cart_item = Cart.objects.get(user=user.id, item=item_id)
            user_cart_item.delete()

            cart = Cart.objects.filter(user=user.id)
            cart_serializer = CartSerializer(cart, many=True)
            return Response(len(cart_serializer.data))
        except:
            cart = Cart.objects.filter(user=user.id)
            cart_serializer = CartSerializer(cart, many=True)
            return Response(len(cart_serializer.data))

class UserLike(APIView):
    def get(self, request, user_name):
        user = User.objects.get(email=user_name)
        try:
            user_like = Like.objects.filter(user=user.id)
            like_serializer = LikeSerializer(user_like, many=True)
            return Response(like_serializer.data)
        except:
            return Response("No Content")

class AddLikeItem(APIView):
    def put(self, request, user_name, item_id):
        user = User.objects.get(email=user_name)
        try:
            user_like_item = Like.objects.get(user=user.id, item=item_id)
            return Response("Already Exist")
        except:
            item = Item.objects.get(id=item_id)
            new_user_like_item = Like(user=user, item=item)
            new_user_like_item.save()
            return Response("Success")

    def delete(self, request, user_name, item_id):
        user = User.objects.get(email=user_name)
        try:
            user_like_item = Like.objects.get(user=user.id, item=item_id)
            user_like_item.delete()
            return Response("Delete Success")
        except:
            return Response("Delete Fail")

class UserRecent(APIView):
    def get(self, request, user_name):
        user = User.objects.get(email=user_name)
        try:
            user_recent_item = RecentLookUp.objects.filter(user=user.id)
            recent_Serializer = RecentLookUpSerializer(user_recent_item, many=True)
            return Response(recent_Serializer.data)
        except:
            return Response(None)

class AddRecentLookUp(APIView):
    def put(self, request, user_name,item_id):
        user = User.objects.get(email=user_name)
        try:
            user_recent_item = RecentLookUp.objects.get(user=user.id, item=item_id)
            user_recent_item.delete()
            new_item = Item.objects.get(id=item_id)
            new_user_recent_item = RecentLookUp(user=user, item=new_item)
            new_user_recent_item.save()
            return Response("Success to Add Existed Item")
        except:
            new_item = Item.objects.get(id=item_id)
            new_user_recent_item = RecentLookUp(user=user, item=new_item)
            new_user_recent_item.save()
            return Response("Success to Add New Item")

class SearchKeyword(APIView):
    def get(self, request, keyword):
        try:
            item = Item.objects.filter(name__contains=keyword)
            search_serializer = ItemSerializer(item, many=True)
            return Response(search_serializer.data)
        except:
            return Response(None)

class AddUserCardInfo(APIView):
    def put(self, request, user_name, card_num, card_name, validate_num, cvc):
        user = User.objects.get(email=user_name)
        new_user_card = UserCard(user=user, card_num=card_num, card_name=card_name, card_validate_num=validate_num, card_cvc=cvc)
        new_user_card.save()
        return Response("Success")

