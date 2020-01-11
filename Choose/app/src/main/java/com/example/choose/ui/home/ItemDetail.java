package com.example.choose.ui.home;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.choose.R;

public class ItemDetail extends AppCompatActivity {
    private ImageView imageView;
    private TextView title, desc;
    private ImageButton like, addCart;
    private Button buy;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)this).getSupportActionBar().hide();
        setContentView(R.layout.activity_item_detail);

        imageView = findViewById(R.id.detail_image);
        title = findViewById(R.id.detail_title);
        desc = findViewById(R.id.detail_desc);
        like = findViewById(R.id.like);
        addCart = findViewById(R.id.cart);
        buy = findViewById(R.id.buy);

        intent = getIntent();
        imageView.setImageResource(Integer.parseInt(intent.getStringExtra("image")));
        title.setText(intent.getStringExtra("title"));
        desc.setText(intent.getStringExtra("desc"));

        // ************* 최근 본 상품 DB에 추가 *************** //
        //
        //

        like.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("@","~~");
                // *********** 좋아요 DB에 추가 ************ //
            }
        });
        addCart.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("@","!!");
                // *********** 장바구니 DB에 추가 ************ //
            }
        });
        buy.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ************* 구매 화면으로 전환 *************** //
            }
        });




    }

}
