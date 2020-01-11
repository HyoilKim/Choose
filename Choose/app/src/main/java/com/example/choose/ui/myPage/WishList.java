package com.example.choose.ui.myPage;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.choose.R;

import java.util.ArrayList;

public class WishList extends AppCompatActivity {
    private ArrayList<WishListItem> wishList;
    private RecyclerView recyclerView;
    private Adapter adapter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        wishList = new ArrayList<>();
        wishList.add(new WishListItem("", "생수1.5L", "1,500"));
        wishList.add(new WishListItem("", "생수3.5L", "2,500"));
        wishList.add(new WishListItem("", "생수5.5L", "3,500"));

        adapter = new Adapter(wishList);
        recyclerView.setAdapter(adapter);
        // ************* Bottom navigator bar set visible(hardware back key) **************** //

//        setFocusableInTouchMode(true);
//        requestFocus();
//        setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
//                getFragmentManager().popBackStack();
//                return false;
//            }
//        });
    }
}
