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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.choose.R;
import com.example.choose.RetrofitAPI;
import com.example.choose.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyPage extends Fragment {
    private ImageButton likeItem;
    private TextView login2;
    private Retrofit mRetrofit;
    private RetrofitAPI mRetrofitAPI;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);
//        login1 = view.findViewById(R.id.login1);
        login2 = view.findViewById(R.id.login2);

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.249.19.252:2680")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRetrofitAPI = mRetrofit.create(RetrofitAPI.class);

        if (UserInfo.isIsLogin()) {
            login2.setText(UserInfo.getEmail() + " 님 안녕하세요.");
        }

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
                        login(idText.getText().toString().trim(), passwordText.getText().toString().trim());
                        popupWindow.dismiss();
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



    public void login(String email, String password) {
        mRetrofitAPI.getUserData(email).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                UserData user = response.body();

                Log.d("Print", "id : " + user.getId() + ", email : " + user.getEmail() + ", password : " + user.getPassword() +
                        ", Birthday : " + user.getBirthday());
                if (password.equals(user.getPassword())) {
                    UserInfo.setId(user.getId());
                    UserInfo.setEmail(user.getEmail());
                    UserInfo.setPassword(user.getPassword());
                    UserInfo.setIsLogin(true);
                    login2.setText(UserInfo.getEmail() + " 님 안녕하세요.");
                } else {
                    Toast.makeText(getContext(), "회원정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.d("Print", "-------------------Failure-----------------");
                Toast.makeText(getContext(), "존재하지 않는 계정 입니다.", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }
}

