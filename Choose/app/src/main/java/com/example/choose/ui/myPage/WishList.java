package com.example.choose.ui.myPage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.choose.R;
import com.example.choose.RetrofitStatic;
import com.example.choose.UserInfo;
import com.example.choose.ui.home.ItemData;
import com.google.gson.annotations.Expose;

import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishList extends AppCompatActivity {
    private ArrayList<WishListItem> wishList;
    private RecyclerView recyclerView;
    private Adapter adapter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        wishList = new ArrayList<>();

        RetrofitStatic.getmRetrofitAPI().getUserWish(UserInfo.getEmail()).enqueue(new Callback<ArrayList<UserWish>>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onResponse(Call<ArrayList<UserWish>> call, Response<ArrayList<UserWish>> response) {
                ArrayList<UserWish> userWishes = response.body();

                if (userWishes.size() != 0) {
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            try {
                                for (UserWish item : userWishes) {
                                    Log.d("PRINT", "id : " + item.getId() + ", item : " + item.getItem() + ", user : " + item.getUser());

                                    Call<ItemData> getOneItem = RetrofitStatic.getmRetrofitAPI().getOneItem(item.getItem());
                                    ItemData i = getOneItem.execute().body();

                                    Log.d("Print", "id : " + i.getId() + ", name : " + i.getName() + ", price : " + i.getPrice() + ", image: " + i.getImage());

                                    wishList.add(new WishListItem(i.getImage(), i.getName(), String.valueOf(i.getPrice()), i.getId()));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            adapter = new Adapter(wishList);
                            recyclerView.setAdapter(adapter);
                        }
                    }.execute();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserWish>> call, Throwable t) {
                Log.d("PRINT", "------------------Failure------------------");
            }
        });
        // ************* Bottom navigator bar set visible(hardware back key) **************** //

//        setFocusableInTouchMode(true);
//        requestFocus();
//        setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
//                getFragmentManager().popBackStack();
//                return false;
//            }
//        });
    }
}
