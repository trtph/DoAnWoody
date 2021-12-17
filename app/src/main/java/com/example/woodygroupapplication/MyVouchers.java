package com.example.woodygroupapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.example.Adapter.VoucherAdapter;
import com.example.model.Voucher;

import java.util.ArrayList;
import java.util.List;

public class MyVouchers extends AppCompatActivity {

    RecyclerView rcvVouchers;
    List<Voucher> vouchers;
    VoucherAdapter vouchersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vouchers);

        rcvVouchers = findViewById(R.id.rcvVouchers);

        LinearLayoutManager manager = new LinearLayoutManager(MyVouchers.this, LinearLayoutManager.VERTICAL, false);
        rcvVouchers.setLayoutManager(manager);

        DividerItemDecoration decoration = new DividerItemDecoration(rcvVouchers.getContext(),DividerItemDecoration.VERTICAL);
        rcvVouchers.addItemDecoration(decoration);

        vouchers = new ArrayList<Voucher>();

        vouchers.add(new Voucher("BINLOVEHIHI","Hi guy, you will get 50% discount on your first order",R.drawable.voucher1));
        vouchers.add(new Voucher("RACINGBOIZ","Hi guy, you will get 30% discount on your first order",R.drawable.voucher1));

        vouchersAdapter = new VoucherAdapter( MyVouchers.this,vouchers);

        rcvVouchers.setAdapter(vouchersAdapter);

    }
}