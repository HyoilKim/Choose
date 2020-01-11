package com.example.choose.ui.cart;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.choose.R;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<CartItemData> itemList;
    private Context context;
    private ArrayAdapter arrayAdapter;
    private TextView priceSumView;
    private ArrayList<Integer> eachItemCount, eachItemPrice;
    String[] spinnerItems = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    public Adapter(Context context, ArrayList<CartItemData> itemList, ArrayList<Integer> eachItemCount, ArrayList<Integer> eachItemPrice) {
        this.itemList = itemList;
        this.context = context;
        this.eachItemCount = eachItemCount;
        this.eachItemPrice = eachItemPrice;
        arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, spinnerItems);
        setSumOfPrice();
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
