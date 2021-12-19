package com.example.woodygroupapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Adapter.FavouriteAdapter;
import com.example.Adapter.ListCategoryAdapter;
import com.example.Adapter.ListProductAdapter;
import com.example.model.FavouriteProduct;
import com.example.model.ListCategoryProduct;
import com.example.model.ListProduct;

import java.util.ArrayList;

public class ListViewProduct extends AppCompatActivity {

    RecyclerView rcvListProduct;
    ListProductAdapter adapter3;
    ArrayList<ListProduct> browseListViewProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_product);
        linkViews();
        configRecyclerView();
        initData();

    }

    private void linkViews() {
        rcvListProduct=findViewById(R.id.rcvListProduct);
    }


    private void configRecyclerView() {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        rcvListProduct.setLayoutManager(gridLayoutManager);

////        DividerItemDecoration decoration=new DividerItemDecoration(rcvListProduct.getContext(),DividerItemDecoration.HORIZONTAL);
////        Drawable drawable= ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_divider);
////        decoration.setDrawable(drawable);
//        rcvListProduct.addItemDecoration(decoration);
    }

    private void initData() {


        browseListViewProduct = new ArrayList<ListProduct>();
        browseListViewProduct.add(new ListProduct(R.drawable.foldingchair, "FOLDING CHAIR", 119.99 ));
        browseListViewProduct.add(new ListProduct(R.drawable.storage, "STORAGE UNIT WOOD",119.99));
        browseListViewProduct.add(new ListProduct(R.drawable.leaning, "LEANING STORAGE",119.99));
        browseListViewProduct.add(new ListProduct(R.drawable.rattan, "RATTAN TROLLEY",119.99));


        adapter3 = new ListProductAdapter(ListViewProduct.this, browseListViewProduct);
       rcvListProduct.setAdapter(adapter3);


    }
}



