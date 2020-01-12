package com.example.choose;

import com.example.choose.ui.home.ItemData;
import com.example.choose.ui.cart.UserCart;
import com.example.choose.ui.myPage.UserData;
import com.example.choose.ui.myPage.UserWish;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitAPI {
    @GET("/get-item/{category}")
    Call<ArrayList<ItemData>> getCategoryItem(@Path("category") String category);

    @GET("/get-user/{email}")
    Call<UserData> getUserData(@Path("email") String email);

    @GET("/get-cart/{email}")
    Call<ArrayList<UserCart>> getUserCart(@Path("email") String email);

    @GET("/get-item/item/{id}")
    Call<ItemData> getOneItem(@Path("id") Integer id);

    @PUT("/get-cart/{email}/{id}/{number}")
    Call<ResponseBody> updateItemCount(@Path("email") String email, @Path("id") Integer id, @Path("number") Integer count);

    @PUT("/add-cart/{email}/{id}")
    Call<ResponseBody> addItemToCart(@Path("email") String email, @Path("id") Integer id);

    @DELETE("/add-cart/{email}/{id}")
    Call<ResponseBody> deleteItemToCart(@Path("email") String email, @Path("id") Integer id);

    @GET("/get-like/{email}")
    Call<ArrayList<UserWish>> getUserWish(@Path("email") String email);

    @DELETE("/add-like/{email}/{id}")
    Call<ResponseBody> deleteItemToLike(@Path("email") String email, @Path("id") Integer id);

    @PUT("/add-like/{email}/{id}")
    Call<ResponseBody> addItemToLike(@Path("email") String email, @Path("id") Integer id);

    @GET("/get-recent/{email}")
    Call<ArrayList<UserWish>> getRecentShow(@Path("email") String email);

    @PUT("/get-recent/{email}/{id}")
    Call<ResponseBody> addRecentView(@Path("email") String email, @Path("id") Integer id);
}
