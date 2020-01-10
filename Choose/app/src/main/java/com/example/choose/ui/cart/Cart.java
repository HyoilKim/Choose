package com.example.choose.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.choose.R;

import java.util.ArrayList;

public class Cart extends Fragment {
    private ArrayList<CartItemData> itemList;
    private RecyclerView recyclerView;
    private Adapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        itemList = new ArrayList<>();
        itemList.add(new CartItemData("1.5L 페트병 물", "1,000", "0", 1, false));
        itemList.add(new CartItemData("1.5L 페트병 물", "1,000", "0", 1, false));
        itemList.add(new CartItemData("1.5L 페트병 물", "1,000", "0", 1, false));
        itemList.add(new CartItemData("1.5L 페트병 물", "1,000", "0", 1, false));
        itemList.add(new CartItemData("3L 페트병 물", "2,000", "0", 1, false));

        adapter = new Adapter(getContext(), itemList);
        recyclerView.setAdapter(adapter);
        return root;
    }
}