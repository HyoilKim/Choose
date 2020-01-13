package com.example.choose.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.choose.R;
import com.example.choose.RetrofitAPI;
import com.example.choose.RetrofitStatic;
import com.example.choose.UserInfo;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemList extends AppCompatActivity {
    GridView gridView;
    Adapter adapter;

    private Retrofit mRetrofit;
    private RetrofitAPI mRetrofitAPI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        View view = getLayoutInflater().inflate(R.layout.activity_main, null, false);
//        view.findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
        setContentView(R.layout.activity_giftlist);
        gridView = (GridView) findViewById(R.id.giftList);

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.249.19.252:2680")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitAPI = mRetrofit.create(RetrofitAPI.class);

        Intent intent = getIntent();
        String category = intent.getStringExtra("name");

        adapter = new Adapter();

        if (intent.getStringExtra("flag") == null) {
            mRetrofitAPI.getCategoryItem(category).enqueue(new Callback<ArrayList<ItemData>>() {
                @Override
                public void onResponse(Call<ArrayList<ItemData>> call, Response<ArrayList<ItemData>> response) {
                    ArrayList<ItemData> items = response.body();

                    if (items.size() != 0) {
                        for (ItemData elem : items) {
                            adapter.addItem(elem);
                            Log.d("Print", "id: " + elem.getId() + " name: " + elem.getName() + " category: " + elem.getCategory() +
                                    " price: " + elem.getPrice() + " image: " + elem.getImage() + " description : " + elem.getDescription() +
                                    ", ViewPager1: " + elem.getViewPagerImage1() + ", ViewPager2: " + elem.getViewPagerImage2() + ", ViewPager3: " +
                                    elem.getViewPagerImage3());
                        }
                        gridView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<ItemData>> call, Throwable t) {
                    Log.d("PRINT", "---------------------Failure----------------------");
                }
            });
        } else if (intent.getStringExtra("flag").equals("SearchResult")) {
            RetrofitStatic.getmRetrofitAPI().getSearchItem(intent.getStringExtra("name")).enqueue(new Callback<ArrayList<ItemData>>() {
                @Override
                public void onResponse(Call<ArrayList<ItemData>> call, Response<ArrayList<ItemData>> response) {
                    ArrayList<ItemData> items = response.body();

                    if (items.size() != 0) {
                        for (ItemData elem : items) {
                            adapter.addItem(elem);
                            Log.d("Print", "id: " + elem.getId() + " name: " + elem.getName() + " category: " + elem.getCategory() +
                                    " price: " + elem.getPrice() + " image: " + elem.getImage() + " description : " + elem.getDescription() +
                                    ", ViewPager1: " + elem.getViewPagerImage1() + ", ViewPager2: " + elem.getViewPagerImage2() + ", ViewPager3: " +
                                    elem.getViewPagerImage3());
                        }
                        gridView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<ItemData>> call, Throwable t) {
                    Log.d("PRINT", "---------------------Failure----------------------");
                }
            });
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (UserInfo.isIsLogin()) {
                    RetrofitStatic.getmRetrofitAPI().addRecentView(UserInfo.getEmail(), adapter.getItem(i).getId()).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Log.d("PRINT", "---------------------Add Recent View Success------------------------");
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d("PRINT", "---------------------Add Recent View Fail---------------------------");
                        }
                    });
                }

                Intent intent = new Intent(getApplicationContext(), ItemDetail.class);
                Log.d("img", adapter.getItem(i).getImage());
                intent.putExtra("ItemId", adapter.getItem(i).getId());
                intent.putExtra("image", adapter.getItem(i).getImage());

                Log.d("PRINT", "-------------------------" + adapter.getItem(i).getViewPagerImage1());
                Log.d("PRINT", "-------------------------" + adapter.getItem(i).getViewPagerImage2());
                Log.d("PRINT", "-------------------------" + adapter.getItem(i).getViewPagerImage3());

                intent.putExtra("ViewPager1", adapter.getItem(i).getViewPagerImage1());
                intent.putExtra("ViewPager2", adapter.getItem(i).getViewPagerImage2());
                intent.putExtra("ViewPager3", adapter.getItem(i).getViewPagerImage3());

                intent.putExtra("title", adapter.getItem(i).getName());
                intent.putExtra("desc", adapter.getItem(i).getDescription());
                startActivity(intent);
            }
        });
    }


    class Adapter extends BaseAdapter {
        ArrayList<ItemData> items = new ArrayList<>();

        @Override
        public int getCount() { return items.size(); }
        public void addItem(ItemData item){ items.add(item); }
        public ItemData getItem(int i) { return items.get(i); }
        public long getItemId(int i) { return i; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ItemViewer itemViewer = new ItemViewer(getApplicationContext());
            itemViewer.setItem(items.get(position));
            return itemViewer;
        }
    }
}
