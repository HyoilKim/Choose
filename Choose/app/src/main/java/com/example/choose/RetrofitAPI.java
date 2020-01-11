package com.example.choose;

import com.example.choose.ui.home.ItemData;
import com.example.choose.ui.cart.UserCart;
import com.example.choose.ui.myPage.UserData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
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
}
