package com.example.choose.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.choose.R;

import java.util.ArrayList;

public class SearchList extends AppCompatActivity {
    private ArrayList<String> searchHistoryList;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;
    private ImageView searchIcon;
    private EditText searchBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchlist);
        initView();
        initListItem();

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, searchHistoryList);
        listView.setAdapter(arrayAdapter);

        searchIcon.bringToFront();
        searchBar.requestFocus();
        searchBar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // DB에 접근하여 검색어에 해당하는 카테고리or이름을 가진 ItemList로 전환 //
                    Log.d("123","Asdf");
                    Intent intent = new Intent(getApplicationContext(), ItemList.class);
                    intent.putExtra("name", searchBar.getText().toString());
                    intent.putExtra("category", searchBar.getText().toString());
                    startActivity(intent);
                }
                return false;
            }
        });
    }

    public void initView() {
        listView = findViewById(R.id.listView);
        searchIcon = findViewById(R.id.searchIcon);
        searchBar = findViewById(R.id.searchBar);
    }

    public void initListItem() {
        searchHistoryList = new ArrayList<>();
        searchHistoryList.add("향수");
        searchHistoryList.add("슬리퍼");
        searchHistoryList.add("방석");
        searchHistoryList.add("핸드크림");
        searchHistoryList.add("립밤");
    }
}

