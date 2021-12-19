package com.example.woodygroupapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.Adapter.ListCategoryAdapter;
import com.example.Adapter.searchAdapter;
import com.example.model.ListCategoryProduct;
import com.example.model.searchProduct;

import java.util.ArrayList;

public class ListAllCategory extends AppCompatActivity {

    RecyclerView rcvAllCateGory;
    ListCategoryAdapter adapter1;
    ArrayList<ListCategoryProduct> browseCategoryProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_category);
        linkViews();
        configRecyclerView();
        initData();
    }

    private void linkViews() {
        rcvAllCateGory=findViewById(R.id.rcvAllCategory);
    }

    private void configRecyclerView() {
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcvAllCateGory.setLayoutManager(manager);

        DividerItemDecoration decoration=new DividerItemDecoration(rcvAllCateGory.getContext(),DividerItemDecoration.HORIZONTAL);
        Drawable drawable= ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_divider);
        decoration.setDrawable(drawable);
        rcvAllCateGory.addItemDecoration(decoration);
    }

    private void initData() {
        browseCategoryProduct = new ArrayList<ListCategoryProduct>();
        browseCategoryProduct.add(new ListCategoryProduct(R.drawable.furniturecollection, "FURNITURE"));
        browseCategoryProduct.add(new ListCategoryProduct(R.drawable.kitchenapplicances, "KITCHEN & APPLIANCES"));
        browseCategoryProduct.add(new ListCategoryProduct(R.drawable.bedroom, "BED & MATTRESSES"));
        browseCategoryProduct.add(new ListCategoryProduct(R.drawable.img_living, "LIVING ROOM"));


        adapter1 = new ListCategoryAdapter(ListAllCategory.this, browseCategoryProduct);
        rcvAllCateGory.setAdapter(adapter1);
    }
}



