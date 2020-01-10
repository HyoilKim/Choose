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

import java.util.ArrayList;

public class GiftList extends AppCompatActivity {
    GridView gridView;
    Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giftlist);

        gridView = (GridView) findViewById(R.id.giftList);

        adapter = new Adapter();
        // *************** DB 정보로 판매 상품들 정보 세팅 ******************** //
        adapter.addItem(new ItemData("A", "123", R.drawable.gift1+""));
        adapter.addItem(new ItemData("B", "456", R.drawable.gift1+""));
        adapter.addItem(new ItemData("C", "789", R.drawable.gift1+""));
        adapter.addItem(new ItemData("D", "000", R.drawable.gift1+""));
        gridView.setAdapter(adapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ItemDetail.class);
                Log.d("img", adapter.getItem(i).getImage());
                intent.putExtra("image", adapter.getItem(i).getImage()+"");
                intent.putExtra("title", adapter.getItem(i).getName());
                intent.putExtra("desc", adapter.getItem(i).getDesc());
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"이름 : "
                        + adapter.getItem(i).getName().toString()
                        + " , Tel : "+adapter.getItem(i).getDesc().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }


    class Adapter extends BaseAdapter {
        ArrayList<ItemData> items = new ArrayList<ItemData>();

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
