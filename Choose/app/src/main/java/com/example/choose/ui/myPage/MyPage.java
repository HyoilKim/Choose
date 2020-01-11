package com.example.choose.ui.myPage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.choose.R;

public class MyPage extends Fragment {
    private ImageButton likeItem;
    private TextView login2;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);
//        login1 = view.findViewById(R.id.login1);
        login2 = view.findViewById(R.id.login2);

        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popupVIew = getLayoutInflater().inflate(R.layout.login_popup, null);
                final PopupWindow popupWindow = new PopupWindow(popupVIew, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);
                popupWindow.showAtLocation(popupVIew, Gravity.CENTER, 0, 0);

                final EditText idText = (EditText) popupVIew.findViewById(R.id.id);
                final EditText passwordText = (EditText) popupVIew.findViewById(R.id.password);

                Button ok = (Button) popupVIew.findViewById(R.id.Ok);
                ok.setText("SIGN IN");
                Button cancel = (Button) popupVIew.findViewById(R.id.Cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
//                        background.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // ************* DB정보와 일치 유무 ***************//
                        Log.d("try", "sign in");
                        login2.setText("OOO님 안녕하세요.");

                    }
                });
            }
        });

        likeItem = view.findViewById(R.id.likeItem);
        likeItem.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WishList.class);
                startActivity(intent);
            }
        });
        return view;
    }
}

