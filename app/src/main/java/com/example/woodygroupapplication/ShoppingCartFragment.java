package com.example.woodygroupapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.ShoppingBagAdapter;
import com.example.model.productshopModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class ShoppingCartFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;

    static ConstraintLayout constraintCart, constraintEmpty;
    RecyclerView rcvProduct;
    ShoppingBagAdapter adapter;
    ArrayList<productshopModel> productshopModels;
    static TextView txtToTal;

    MaterialButton btnCheckout;
    ImageView imvAddNumber, imvDecreseNumber;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        rcvProduct=view.findViewById(R.id.rcvProduct);
        btnCheckout= view.findViewById(R.id.btnCheckout);
        txtToTal = view.findViewById(R.id.txtTotal);
        imvAddNumber = view.findViewById(R.id.imvAddNumber);
        imvDecreseNumber = view.findViewById(R.id.imvAddNumber);
        constraintCart = view.findViewById(R.id.constraintCart);
        constraintEmpty = view.findViewById(R.id.constraintEmpty);




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

        //Insert data
        db.collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                        String documentID = documentSnapshot.getId();

                        productshopModel cartModel = documentSnapshot.toObject(productshopModel.class);

                        cartModel.setDocumentID(documentID);

                        productshopModels.add(cartModel);
                        adapter.notifyDataSetChanged();
                    }
                }
                //Switch constraintLayout
                SwitchLayout(productshopModels);
                caculateTotalAmount(productshopModels);
            }
        });

        //Open Checkout Activity
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Checkout_Layout.class));
            }
        });
        return view;
    }

    public static void SwitchLayout(ArrayList<productshopModel> productshopModels) {
        if (productshopModels.isEmpty()){
            constraintEmpty.setVisibility(View.VISIBLE);
            constraintCart.setVisibility(View.GONE);
        }else if (!productshopModels.isEmpty()){
            constraintEmpty.setVisibility(View.GONE);
            constraintCart.setVisibility(View.VISIBLE);
        }
    }

    public static void caculateTotalAmount(ArrayList<productshopModel> productshopModels) {
        double totalAmount = 0.0;
        for(productshopModel productshopmodel : productshopModels){
            totalAmount += productshopmodel.getTotalPrice();
        }
        txtToTal.setText("$ " + totalAmount);
    }
}
