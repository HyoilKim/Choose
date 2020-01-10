package com.example.choose.ui.home;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.choose.R;

public class ItemDetail extends AppCompatActivity {
    private ImageView imageView;
    private TextView title, desc;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)this).getSupportActionBar().hide();
        setContentView(R.layout.activity_item_detail);

        imageView = findViewById(R.id.detail_image);
        title = findViewById(R.id.detail_title);
        desc = findViewById(R.id.detail_desc);

        intent = getIntent();
        imageView.setImageResource(Integer.parseInt(intent.getStringExtra("image")));
        title.setText(intent.getStringExtra("title"));
        desc.setText(intent.getStringExtra("desc"));

        // ************* 최근 본 상품 DB에 추가 *************** //


    }
}
