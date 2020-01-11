from django.shortcuts import render
from search.serializers import UserSerializer, ItemSerializer, CartSerializer, LikeSerializer
from search.models import User, Item, Cart, Like

from rest_framework.views import APIView
from rest_framework.response import Response

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
    def get(self, request, category, id):
        try:
            item = Item.objects.get(category=category, id=id)
            item_serializer = ItemSerializer(item)
            return Response(item_serializer.data)
        except:
            return Response(None)

class UserCart(APIView):
    def get(self, request, user_name):
        user = User.objects.get(email=user_name)
        try:
            user_cart = Cart.objects.filter(user=user.id)
            cart_serializer = CartSerializer(user_cart, many=True)
            return Response(cart_serializer.data)
        except:
            return Response(None)

class AddCartItem(APIView):
    def put(self, request, user_name, item_id):
        user = User.objects.get(email=user_name)
        try:
            user_cart_item = Cart.objects.get(user=user.id, item=item_id)
            user_cart_item.count += 1
            user_cart_item.save()
            return Response("Success")
        except:
            item = Item.objects.get(id=item_id)
            new_user_cart_item = Cart(user=user, item=item, count= 1)
            new_user_cart_item.save()
            return Response("Success")

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
