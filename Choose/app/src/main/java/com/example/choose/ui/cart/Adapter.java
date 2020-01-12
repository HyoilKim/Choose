package com.example.choose.ui.cart;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<CartItemData> itemList;
    private Context context;
    private ArrayAdapter arrayAdapter;
    private TextView priceSumView;
    private ArrayList<Integer> eachItemCount, eachItemPrice;
    String[] spinnerItems = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

//    private Retrofit mRetrofit;
//    private RetrofitAPI mRetrofitAPI;

    public Adapter(Context context, ArrayList<CartItemData> itemList, ArrayList<Integer> eachItemCount, ArrayList<Integer> eachItemPrice) {
        this.itemList = itemList;
        this.context = context;
        this.eachItemCount = eachItemCount;
        this.eachItemPrice = eachItemPrice;
        arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, spinnerItems);
//        setSumOfPrice();
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_cart, parent, false);

//        mRetrofit = new Retrofit.Builder()
//                .baseUrl("http://192.249.19.252:2680")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        mRetrofitAPI = mRetrofit.create(RetrofitAPI.class);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        CartItemData item = itemList.get(position);
        String title = item.getTitle();
        String price = item.getPrice();
        String image = item.getImage();
        int count = item.getCount();
        boolean isCheck = item.isCheck();

        holder.title.setText(title);
        holder.price.setText(price);
        holder.spinner.setAdapter(arrayAdapter);
        holder.spinner.setSelected(false);
        holder.spinner.setSelection(count - 1, false);
        Glide.with(context).load("http://192.249.19.252:2680" + item.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private void updateNumber(String email, int itemId, int number) {
        RetrofitStatic.getmRetrofitAPI().updateItemCount(email, itemId, number).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("PRINT", "------------------Success-----------------");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("PRINT", "------------------Failure-----------------");
            }
        });
    }

    private void deleteItem(String email, int itemId) {
        RetrofitStatic.getmRetrofitAPI().deleteItemToCart(email, itemId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("PRINT", "------------------Delete Success-----------------");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("PRINT", "------------------Delete Failure-----------------");
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, price;
        ImageView image;
        Spinner spinner;
        Button delete;
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.cart_title);
            price = (TextView) itemView.findViewById(R.id.cart_price);
            image = (ImageView) itemView.findViewById(R.id.cart_image);
            delete = (Button) itemView.findViewById(R.id.cart_deleteButton);
            spinner = (Spinner) itemView.findViewById(R.id.cart_spinner);
            checkBox = (CheckBox) itemView.findViewById(R.id.cart_checkBox);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        deleteItem(UserInfo.getEmail(), itemList.get(getAdapterPosition()).getItemId());
                        itemList.remove(pos);
                        notifyItemRemoved(pos);
                        eachItemCount.remove(pos);
                        eachItemPrice.remove(pos);
                        // 총 합 다시 계산
                        setSumOfPrice();
                    }
                }
            });
            // 체크 되어 있으면, spinner 에서 선택된 개수를 eachItemCount에 세팅
            //      되어 있지 않으면, count 0으로 세팅
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        CartItemData item = itemList.get(pos);
                        if (checkBox.isChecked()) {
                            Log.d("check","~~");
                            eachItemCount.set(pos, Integer.valueOf(spinner.getSelectedItem().toString()));
                        } else {
                            Log.d("uncheck","~~");
                            eachItemCount.set(pos, 0);
                        }
                        // 총 합 재세팅
                        setSumOfPrice();
                    }
                }
            });
            // spinner가 클릭 되었을 때, checkBox가 체크 되어 있다면 eachitemCount를 0으로 세팅
            //                                      체크 되어 있지 않다면, spinner 값으로 세팅
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int itemPos = getAdapterPosition();
                    int count = Integer.parseInt(spinnerItems[position]);

                    Log.d("Print", "~~~~~~" + itemList.get(itemPos).getItemId() + ", " + count);

                    updateNumber(UserInfo.getEmail(), itemList.get(itemPos).getItemId(), count);

                    if (checkBox.isChecked()) {
                        eachItemCount.set(itemPos, count);
                    } else {
                        eachItemCount.set(itemPos, 0);
                    }
                    // 총 합 재세팅
                    setSumOfPrice();
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }

    public void setSumOfPrice() {
        int sum = 0;
        for (int i = 0; i < eachItemCount.size(); i++) {
            sum += eachItemCount.get(i)*eachItemPrice.get(i);
        }
        Cart.sumOfPrice.setText(sum+"");
    }

    public void addItem(int price, int count) {
        eachItemCount.add(count);
        eachItemPrice.add(price);
    }
}
