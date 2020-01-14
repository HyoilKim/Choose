from rest_framework import serializers
from .models import User, Item, Cart, Like, RecentLookUp, UserCard

class UserSerializer(serializers.ModelSerializer):
    cart = serializers.StringRelatedField(many=True)

    class Meta:
        model = User
        fields = ('id', 'email', 'password', 'birthday', 'cart')

class ItemSerializer(serializers.ModelSerializer):
    cart = serializers.StringRelatedField(many=True)

    class Meta:
        model = Item
        fields = ('id','name', 'category', 'price', 'image', 'description','cart', 'view_pager_image_1'
        , 'view_pager_image_2', 'view_pager_image_3', 'age')

class CartSerializer(serializers.ModelSerializer):
    class Meta:
        model = Cart
        fields = ('id', 'user', 'item', 'count')

class LikeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Like
        fields = ('id', 'user', 'item')

class RecentLookUpSerializer(serializers.ModelSerializer):
    class Meta:
        model = RecentLookUp
        fields = ('id', 'user', 'item')

class UserCardSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserCard
        fields = ('id', 'card_num', 'card_name', 'card_validate_num', 'card_cvc', 'user')