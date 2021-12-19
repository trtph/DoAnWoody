package com.example.woodygroupapplication;

<<<<<<< HEAD
import android.content.Intent;
=======
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
>>>>>>> master
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.ShoppingBagAdapter;
import com.example.model.productshopModel;
<<<<<<< HEAD
import com.google.android.material.button.MaterialButton;
=======
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
>>>>>>> master

import java.util.ArrayList;


public class ShoppingCartFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;

    RecyclerView rcvProduct;
    ShoppingBagAdapter adapter;
    ArrayList<productshopModel> productshopModels;
    TextView txtToTal;
    LinearLayout layoutdelete;

    MaterialButton btnCheckout;

    MaterialButton btnCheckout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        rcvProduct=view.findViewById(R.id.rcvProduct);
        btnCheckout= view.findViewById(R.id.btnCheckout);
<<<<<<< HEAD
=======
        txtToTal = view.findViewById(R.id.txtTotal);
        layoutdelete = view.findViewById(R.id.layoutDelete);
>>>>>>> master

        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(mMessageReceiver, new IntentFilter("MyTotalAmount"));

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcvProduct.setLayoutManager(manager);

        DividerItemDecoration decoration=new DividerItemDecoration(rcvProduct.getContext(),DividerItemDecoration.HORIZONTAL);
        Drawable drawable= ContextCompat.getDrawable(getContext(), R.drawable.custom_divider);
        decoration.setDrawable(drawable);
        rcvProduct.addItemDecoration(decoration);

        productshopModels = new ArrayList<>();
        adapter = new ShoppingBagAdapter(getContext(),productshopModels);
        rcvProduct.setAdapter(adapter);

<<<<<<< HEAD
=======
        //Insert data
        db.collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                        productshopModel cartModel = documentSnapshot.toObject(productshopModel.class);
                        productshopModels.add(cartModel);
                        adapter.notifyDataSetChanged();
                    }
                }

            }
        });

>>>>>>> master
        //Open Checkout Activity
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Checkout_Layout.class));
            }
        });
<<<<<<< HEAD

=======
//        caculateCart();
>>>>>>> master
        return view;
    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalBill = intent.getIntExtra("totalAmount",0);
            txtToTal.setText("$ " + totalBill);
        }
    };

//    public String caculateCart() {
//        String total = 0;
//        for (int i = 0; i < productshopModels.size(); i++) {
//                total = total + (productshopModels.get(i).getPrPrice());
//        }
//        txtToTal.setText("$ " + total);
//        return total;
//
//    }
}
