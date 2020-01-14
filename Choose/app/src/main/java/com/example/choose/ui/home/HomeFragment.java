package com.example.choose.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.choose.R;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class HomeFragment extends Fragment {
    private ImageButton birthdayButton, moveButton;
    private ImageView searchIcon;
    private TextView searchMenuBar;
    private View root;

    AutoScrollViewPager autoViewPager;
    ArrayList<String>data =new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("츄즈");
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        setButtonListener();

        ArrayList<String> data = new ArrayList<>(); //이미지 url를 저장하는 arraylist
        data.add("https://i.ibb.co/d42xcJq/ad1.jpg");
        data.add("https://i.ibb.co/RgfGRB4/ad2.jpg");
        data.add("https://i.ibb.co/P60hhJs/ad3.jpg");

        autoViewPager = (AutoScrollViewPager)root.findViewById(R.id.autoViewPager);
        AutoScrollAdapter scrollAdapter = new AutoScrollAdapter(getContext(), data);
        autoViewPager.setAdapter(scrollAdapter); //Auto Viewpager에 Adapter 장착
        autoViewPager.setInterval(3000); // 페이지 넘어갈 시간 간격 설정
        autoViewPager.startAutoScroll(); //Auto Scroll 시작

        searchMenuBar = root.findViewById(R.id.searchBar);
        searchMenuBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchList.class);
                startActivity(intent);
            }
        });
        return root;
    }

    public void initView(){
        birthdayButton = root.findViewById(R.id.birthday);
        moveButton = root.findViewById(R.id.parent);
        searchIcon = root.findViewById(R.id.searchIcon);
        searchIcon.bringToFront();
    }

    public void setButtonListener() {
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ItemList.class);
                switch (v.getId()) {
                    case R.id.birthday:
                        intent.putExtra("name","생일");
                        break;
                    case R.id.parent:
                        intent.putExtra("name","효도");
                        break;
                    default:
                        break;
                }
                startActivity(intent);
            }
        };

        birthdayButton.setOnClickListener(listener);
        moveButton.setOnClickListener(listener);
    }
}