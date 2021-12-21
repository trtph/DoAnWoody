package com.example.woodygroupapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.Adapter.ListCategoryAdapter;
import com.example.Adapter.searchAdapter;
import com.example.model.ListCategoryProduct;
import com.example.model.searchProduct;

import java.util.ArrayList;

public class ListAllRooms extends AppCompatActivity {

    RecyclerView rcvAllRooms;
    ImageView imvBack;
    ListCategoryAdapter adapter;
    ArrayList<ListCategoryProduct> browseRoomProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_rooms);
        linkViews();
        configRecyclerView();
        initData();
        onClick();

    }

    private void onClick() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack();
                finish();
            }
        });
    }

    private void linkViews() {
        rcvAllRooms=findViewById(R.id.rcvAllRooms);
        imvBack = findViewById(R.id.imvBack);
    }


    private void configRecyclerView() {
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcvAllRooms.setLayoutManager(manager);

        DividerItemDecoration decoration=new DividerItemDecoration(rcvAllRooms.getContext(),DividerItemDecoration.HORIZONTAL);
        Drawable drawable= ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_divider);
        decoration.setDrawable(drawable);
        rcvAllRooms.addItemDecoration(decoration);
    }

    private void initData() {


        browseRoomProduct = new ArrayList<ListCategoryProduct>();
        browseRoomProduct.add(new ListCategoryProduct(R.drawable.livingroom, "LIVING ROOM"));
        browseRoomProduct.add(new ListCategoryProduct(R.drawable.diningroom, "DINING ROOM"));
        browseRoomProduct.add(new ListCategoryProduct(R.drawable.img_1, "BATHROOM"));
        browseRoomProduct.add(new ListCategoryProduct(R.drawable.img_2, "BEDROOM"));


        adapter = new ListCategoryAdapter(ListAllRooms.this, browseRoomProduct);
        rcvAllRooms.setAdapter(adapter);


    }
    }



