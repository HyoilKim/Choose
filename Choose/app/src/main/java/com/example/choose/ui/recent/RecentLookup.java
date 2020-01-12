package com.example.choose.ui.recent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.choose.R;
import com.example.choose.RetrofitStatic;
import com.example.choose.UserInfo;
import com.example.choose.ui.home.ItemData;
import com.example.choose.ui.home.ItemDetail;
import com.example.choose.ui.myPage.UserWish;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecentLookup extends Fragment {

    private Adapter adapter;
    private GridView gridView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recent, container, false);
        ArrayList<ItemData> recentShow = new ArrayList<>();
//        int img[] = {R.drawable.gift1, R.drawable.gift, R.drawable.gift, R.drawable.gift1};

        RetrofitStatic.getmRetrofitAPI().getRecentShow(UserInfo.getEmail()).enqueue(new Callback<ArrayList<UserWish>>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onResponse(Call<ArrayList<UserWish>> call, Response<ArrayList<UserWish>> response) {
                ArrayList<UserWish> getRecentShow = response.body();

//                for (UserWish item : recentShow) {
//                    Log.d("PRINT", "id : " + item.getId() + ", item : " + item.getItem() + ", user: " + item.getUser());
//                }
                if (getRecentShow.size() != 0) {
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            try {
                                for (UserWish item : getRecentShow) {
                                    Log.d("PRINT", "id : " + item.getId() + ", item : " + item.getItem() + ", user : " + item.getUser());

                                    Call<ItemData> getOneItem = RetrofitStatic.getmRetrofitAPI().getOneItem(item.getItem());
                                    ItemData i = getOneItem.execute().body();

                                    Log.d("Print", "id : " + i.getId() + ", name : " + i.getName() + ", price : " + i.getPrice() + ", image: " + i.getImage());

                                    recentShow.add(new ItemData(i.getId(), i.getName(), i.getCategory(), i.getPrice(), i.getImage(), i.getDescription()));
                                }
                            } catch(Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            adapter = new Adapter(getContext(), R.layout.item_recent_lookup, recentShow);
                            gridView = (GridView) root.findViewById(R.id.gridView);
                            gridView.setAdapter(adapter);

                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Log.d("asdf", recentShow.get(position).getId() + " ");
                                    
                                    Intent intent = new Intent(getContext(), ItemDetail.class);
                                    Log.d("img", recentShow.get(position).getImage());
                                    intent.putExtra("ItemId", recentShow.get(position).getId());
                                    intent.putExtra("image", recentShow.get(position).getImage());
                                    intent.putExtra("title", recentShow.get(position).getName());
                                    intent.putExtra("desc", recentShow.get(position).getDescription());
                                    startActivity(intent);
                                }
                            });
                        }
                    }.execute();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserWish>> call, Throwable t) {

            }
        });

        return root;
    }
}

class Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<ItemData> recentShow;
    LayoutInflater inf;

    public Adapter(Context context, int layout, ArrayList<ItemData> recentShow) {
        this.context = context;
        this.layout = layout;
        this.recentShow = recentShow;
        inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return recentShow.size();
    }

    @Override
    public Object getItem(int position) {
        return recentShow.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inf.inflate(layout, null);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.recent_lookup_image);
        Glide.with(context).load("http://192.249.19.252:2680" + recentShow.get(position).getImage()).into(imageView);

        return convertView;
    }
}