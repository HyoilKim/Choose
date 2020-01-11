package com.example.choose;

import com.example.choose.ui.home.ItemData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitAPI {
    @GET("/get-item/{category}")
    Call<ArrayList<ItemData>> getCategoryItem(@Path("category") String category);
}
