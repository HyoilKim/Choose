package com.example.choose.ui.myPage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.choose.R;
import com.example.choose.RetrofitStatic;
import com.example.choose.UserInfo;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<WishListItem> wishList;
    private Context context;

    public Adapter(ArrayList<WishListItem> wishList) {
        this.wishList = wishList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_wishlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        WishListItem item = wishList.get(position);
        String title = item.getTitle();
        String price = item.getPrice();
//        String image = item.getImage();

        holder.title.setText(title);
        holder.price.setText(price);
        Glide.with(context).load("http://192.249.19.252:2680" + wishList.get(position).getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return wishList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, price;
        ImageView image;
        Button delete, addCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.wish_title);
            price = itemView.findViewById(R.id.wish_price);
            image = itemView.findViewById(R.id.wish_image);
            delete = itemView.findViewById(R.id.wish_deleteButton);
            addCart = itemView.findViewById(R.id.wish_addCartButton);

            delete.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        deleteItemToLike(UserInfo.getEmail(), wishList.get(pos).getItemId());
                        wishList.remove(pos);
                        notifyItemRemoved(pos);
                    }
                }
            });
            addCart.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // ********** 장바구니 DB에 저장 ************ //
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        addItemToCart(UserInfo.getEmail(), wishList.get(pos).getItemId());
                        deleteItemToLike(UserInfo.getEmail(), wishList.get(pos).getItemId());
                        wishList.remove(pos);
                        notifyItemRemoved(pos);
                    }
                }
            });
        }

    }

    private void deleteItemToLike(String email, int id) {
        RetrofitStatic.getmRetrofitAPI().deleteItemToLike(email, id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("PRINT", "-------------------Delete Like Item Success------------------");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("PRINT", "-------------------Delete Like Item Failure------------------");
            }
        });
    }

    private void addItemToCart(String email, int id) {
        RetrofitStatic.getmRetrofitAPI().addItemToCart(email, id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("PRINT", "-------------------Add Like Item To Cart Success------------------");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("PRINT", "-------------------Add Like Item To Cart Fail---------------------");
            }
        });
    }
}
