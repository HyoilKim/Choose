package com.example.choose.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.choose.R;

public class HomeFragment extends Fragment {
    Button birthdayButton, moveButton;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        setButtonListener();

        return root;
    }

    public void initView(){
        birthdayButton = root.findViewById(R.id.birthday);
        moveButton = root.findViewById(R.id.move);
    }

    public void setButtonListener() {
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GiftList.class);
                switch (v.getId()) {
                    case R.id.birthday:
                        intent.putExtra("name","birthday");
                        break;
                    case R.id.move:
                        intent.putExtra("name","move");
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