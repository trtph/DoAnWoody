package com.example.woodygroupapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {



    BottomNavigationView navigationView;
    FrameLayout frame_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkViews();
        addEvents();

    }

    private void linkViews() {
        navigationView = findViewById(R.id.bottom_navigation);
        frame_content = findViewById(R.id.frame_content);

    }

    private void addEvents() {


        navigationView.setOnItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new HomeFragment()).commit();
    }
    NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()){
                case R.id.navHome:
                    fragment = new HomeFragment();
                    break;

                case R.id.navSearch:
                    fragment = new SearchFragment();
                    break;

                case R.id.navFavorite:
                    fragment = new FavoriteFragment();
                    break;

                case R.id.navShoppingCart:
                    fragment = new ShoppingCartFragment();
                    break;

                case R.id.navUserAccount:
                    fragment = new UserAccountActivity();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, fragment).addToBackStack(null).commit();
            return true;
        }
    };
}