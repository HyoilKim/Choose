package com.example.choose;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.choose.ui.cart.UserCart;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static TextView badge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_myPage)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) navView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(2);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;

        View badgeLayout = LayoutInflater.from(this)
                .inflate(R.layout.bottom_nav_badge, itemView, true);
        badge = badgeLayout.findViewById(R.id.badge);
        if (UserInfo.isIsLogin()) {
            RetrofitStatic.getmRetrofitAPI().getUserCart(UserInfo.getEmail()).enqueue(new Callback<ArrayList<UserCart>>() {
                @Override
                public void onResponse(Call<ArrayList<UserCart>> call, Response<ArrayList<UserCart>> response) {
                    ArrayList<UserCart> userCart = response.body();
                    if (userCart.size() != 0) {
                        badge.setText(userCart.size() + "+");
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<UserCart>> call, Throwable t) {
                    Log.d("PRINT", "------------------------Failure------------------------");
                }
            });
        } else {
            badge.setText("0+");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("PRINT", "----------------------MainActivity OnResume-----------------------");
    }
}
