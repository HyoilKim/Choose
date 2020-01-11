from rest_framework import serializers
from .models import User, Item, Cart, Like

class UserSerializer(serializers.ModelSerializer):
    cart = serializers.StringRelatedField(many=True)

    class Meta:
        model = User
        fields = ('id', 'email', 'password', 'birthday', 'cart')

class ItemSerializer(serializers.ModelSerializer):
    cart = serializers.StringRelatedField(many=True)

    class Meta:
        model = Item
        fields = ('id','name', 'category', 'price', 'image', 'cart')

class CartSerializer(serializers.ModelSerializer):
    class Meta:
        model = Cart
        fields = ('id', 'user', 'item', 'count')

class LikeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Like
        fields = ('id', 'user', 'item')