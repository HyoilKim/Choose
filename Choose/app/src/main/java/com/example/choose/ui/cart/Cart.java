package com.example.choose.ui.cart;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.choose.R;
import com.example.choose.RetrofitAPI;
import com.example.choose.UserInfo;
import com.example.choose.ui.home.ItemData;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cart extends Fragment {
    private ArrayList<CartItemData> itemList;
    private RecyclerView recyclerView;
    private Adapter adapter;
    public static TextView sumOfPrice;

    private Retrofit mRetrofit;
    private RetrofitAPI mRetrofitAPI;

    private ArrayList<Integer> eachItemCount;
    private ArrayList<Integer> eachItemPrice;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().findViewById(R.id.nav_view).setVisibility(View.GONE);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        sumOfPrice = root.findViewById(R.id.priceSum);

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.249.19.252:2680")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRetrofitAPI = mRetrofit.create(RetrofitAPI.class);

        recyclerView = root.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        itemList = new ArrayList<>();
        eachItemCount = new ArrayList<>();
        eachItemPrice = new ArrayList<>();

        mRetrofitAPI.getUserCart(UserInfo.getEmail()).enqueue(new Callback<ArrayList<UserCart>>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onResponse(Call<ArrayList<UserCart>> call, Response<ArrayList<UserCart>> response) {
                ArrayList<UserCart> cart = response.body();

                if (cart.size() != 0) {
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            try {
                                for (UserCart item : cart) {
                                    Log.d("Print", "id : " + item.getId() + ", userId : " + item.getUserId() + ", item : " + item.getItemId() +
                                            ", count : " + item.getCount());

                                    Call<ItemData> getOneItem = mRetrofitAPI.getOneItem(item.getItemId());

                                    ItemData i = getOneItem.execute().body();

                                    Log.d("Print", "id : " + i.getId() + ", userId : " + i.getName() + ", item : " + i.getPrice());

                                    itemList.add(new CartItemData(i.getName(), i.getDescription(), String.valueOf(i.getPrice()), i.getImage(), i.getId(), item.getCount(), false));
                                    eachItemPrice.add(i.getPrice());
                                    eachItemCount.add(item.getCount());
                                }
                            } catch (IOException e) {

                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            adapter = new Adapter(getContext(), itemList, eachItemCount, eachItemPrice);
                            recyclerView.setAdapter(adapter);
                        }
                    }.execute();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserCart>> call, Throwable t) {
                Log.d("Print", "-------------------Failure------------------");
            }
        });


        // ************* Bottom navigator bar set visible(hardware back key) **************** //
        root.setFocusableInTouchMode(true);
        root.requestFocus();
        root.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                getActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
                getFragmentManager().popBackStack();
                return false;
            }
        });
        return root;
    }
}