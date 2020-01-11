package com.example.choose.ui.cart;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.choose.MainActivity;
import com.example.choose.R;

import java.util.ArrayList;
import java.util.List;

public class Cart extends Fragment {
    private ArrayList<CartItemData> itemList;
    private RecyclerView recyclerView;
    private Adapter adapter;
    public static TextView sumOfPrice;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().findViewById(R.id.nav_view).setVisibility(View.GONE);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        sumOfPrice = root.findViewById(R.id.priceSum);

        recyclerView = root.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        itemList = new ArrayList<>();
        // ************* DB : 장바구니 정보 받아오기 **************//
        ArrayList<Integer> eachItemCount = new ArrayList<>(); eachItemCount.add(1); eachItemCount.add(1); eachItemCount.add(1); eachItemCount.add(1);
        ArrayList<Integer> eachItemPrice = new ArrayList<>(); eachItemPrice.add(1000); eachItemPrice.add(2000); eachItemPrice.add(3000); eachItemPrice.add(4000);

        itemList.add(new CartItemData("1.5L 페트병 물", "1,000", "0", 1, false));
        itemList.add(new CartItemData("3.5L 페트병 물", "2,000", "0", 1, false));
        itemList.add(new CartItemData("4.5L 페트병 물", "3,000", "0", 1, false));
        itemList.add(new CartItemData("6L 페트병 물", "4,000", "0", 1, false));

        adapter = new Adapter(getContext(), itemList, eachItemCount, eachItemPrice);
        recyclerView.setAdapter(adapter);


        // ************* Bottom navigator bar set visible(hardware back key) **************** //
        root.setFocusableInTouchMode(true);
        root.requestFocus();
        root.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                getActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
                getFragmentManager().popBackStack();
                return false;
            }
        });
        return root;
    }
}