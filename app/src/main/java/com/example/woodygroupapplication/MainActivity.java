package com.example.woodygroupapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.ShoppingBagAdapter;
import com.example.model.productshopModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

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
                    fragment = new com.example.woodygroupapplication.SearchFragment();
                    break;

                case R.id.navFavorite:
                    fragment = new com.example.woodygroupapplication.FavoriteFragment();
                    break;

                case R.id.navShoppingCart:
                    fragment = new ShoppingCartFragment();
                    break;

                case R.id.navUserAccount:
                    fragment = new UserAccountFragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, fragment).commit();
            return true;
        }
    };
}