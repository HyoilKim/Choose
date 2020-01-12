package com.example.choose;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitStatic {
    private static Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl("http://192.249.19.252:2680")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static RetrofitAPI mRetrofitAPI = mRetrofit.create(RetrofitAPI.class);

    public static Retrofit getmRetrofit() {
        return mRetrofit;
    }

    public static RetrofitAPI getmRetrofitAPI() {
        return mRetrofitAPI;
    }
}
