package com.example.choose.ui.cart;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.choose.R;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<CartItemData> itemList;
    private Context context;
    private ArrayAdapter arrayAdapter;
    String[] items = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    public Adapter(Context context, ArrayList<CartItemData> itemList) {
        this.itemList = itemList;
        this.context = context;
        arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, items);
        Log.d("itemList size", itemList.size()+"");
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_cart, parent, false);
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
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, price, count;
        ImageView image;
        Spinner spinner;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.cart_title);
            price = (TextView) itemView.findViewById(R.id.cart_price);
            image = (ImageView) itemView.findViewById(R.id.cart_image);
            spinner = (Spinner) itemView.findViewById(R.id.cart_spinner);
        }
    }

}
