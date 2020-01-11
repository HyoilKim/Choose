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

import com.example.choose.R;

import java.util.ArrayList;

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
                        wishList.remove(pos);
                        notifyItemRemoved(pos);
                    }
                }
            });
            addCart.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // ********** 장바구니 DB에 저장 ************ //
                }
            });
        }

    }
}
