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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.choose.R;
import com.example.choose.RetrofitStatic;
import com.example.choose.UserInfo;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.annotation.Dimension.DP;

public class ItemDetail extends AppCompatActivity {
    private ArrayList<Integer> imageList;
    private ImageView imageView;
    private TextView title, desc;
    private ImageButton like, addCart;
    private Button buy;
    private Intent intent;
    private ViewPager viewPager;

    private int itemId;

    public void initView() {
        imageView = findViewById(R.id.detail_image);
        title = findViewById(R.id.detail_title);
        desc = findViewById(R.id.detail_desc);
        like = findViewById(R.id.like);
        addCart = findViewById(R.id.cart);
        buy = findViewById(R.id.buy);
        viewPager = findViewById(R.id.viewPager);
    }

    public void initImageData() {
        // ************ DB 에서 상품 이미지 *************** //
        imageList = new ArrayList<>();
        imageList.add(R.drawable.user);
        imageList.add(R.drawable.recent);
        imageList.add(R.drawable.cart);
    }

    public void setViewPager() {
        viewPager.setClipToPadding(false);
        float density = getResources().getDisplayMetrics().density;
        int margin = (int) (DP * density);
        viewPager.setPadding(margin, 0, margin, 0);
        viewPager.setPageMargin(margin/2);
        viewPager.setAdapter(new ViewPagerAdapter(this, imageList));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ((AppCompatActivity)this).getSupportActionBar().hide();

        initView();
        initImageData();
        setViewPager();

        intent = getIntent();
        itemId = intent.getIntExtra("ItemId", -1);
        Glide.with(this).load("http://192.249.19.252:2680" + intent.getStringExtra("image")).into(imageView);
        title.setText(intent.getStringExtra("title"));
        desc.setText(intent.getStringExtra("desc"));

        Log.d("Print", "----------------------" + itemId + "-----------------------");

        // ************* 최근 본 상품 DB에 추가 *************** //
        //
        //

        like.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("@","~~");
                // *********** 좋아요 DB에 추가 ************ //
                if (UserInfo.isIsLogin()) {
                    addUserLike(UserInfo.getEmail(), itemId);
                } else {
                    Toast.makeText(getApplicationContext(), "로그인이 필요한 서비스 입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        addCart.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("@","!!");
                // *********** 장바구니 DB에 추가 ************ //
                if (UserInfo.isIsLogin()) {
                    addUserCart(UserInfo.getEmail(), itemId);
                } else {
                    Toast.makeText(getApplicationContext(), "로그인이 필요한 서비스 입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buy.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ************* 구매 화면으로 전환 *************** //
            }
        });

    }

    private void addUserCart(String email, int itemId) {
        RetrofitStatic.getmRetrofitAPI().addItemToCart(email, itemId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("PRINT", "--------------Adding to Cart Success!!-----------------");
                Toast.makeText(getApplicationContext(), "장바구니에 추가 되었습니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("PRINT", "-------------------Adding to Cart Fail!!--------------------");
            }
        });
    }

    private void addUserLike(String email, int itemId) {
        RetrofitStatic.getmRetrofitAPI().addItemToLike(email, itemId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("PRINT", "-------------------Adding to Like Success!!------------------");
                Toast.makeText(getApplicationContext(), "찜한 목록에 추가 되었습니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("PRINT", "-------------------Adding to Like Fail!!--------------------");
            }
        });
    }
}
