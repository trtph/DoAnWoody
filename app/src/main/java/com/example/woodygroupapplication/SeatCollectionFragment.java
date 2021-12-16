package com.example.woodygroupapplication;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.CollectionAdapter;
import com.example.model.Collection;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SeatCollectionFragment extends Fragment {
    RecyclerView rcvSeatCollection;
    //Firebase
    DatabaseReference databaseReference;

    //Variable

    ArrayList<Collection> collectionArrayList;
    CollectionAdapter collectionAdapter;
    Context context;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seat_collection, container, false);
        rcvSeatCollection = view.findViewById(R.id.rcvSeatCollection);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        rcvSeatCollection.setLayoutManager(manager);

        //Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //ArrayList
        collectionArrayList = new ArrayList<>();

        //Get Data Method
        GetDataFromFirebase();

        class SpacesItemDecortion extends RecyclerView.ItemDecoration{
            private final int mSpace;
            public SpacesItemDecortion(int space, int mSpace){
                this.mSpace = mSpace;
            }
            @Override
            public void getItemOffsets(Rect outRect,
                                       View view,
                                       RecyclerView parent, RecyclerView.State state){
                outRect.left = mSpace;
                outRect.right = mSpace;
            }
        }

        rcvSeatCollection.addItemDecoration(new SpacesItemDecortion(50, 30));

        return view;
    }

    private void GetDataFromFirebase() {
        Query query = databaseReference.child("Collection").child("seatCollection");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    Collection collection = new Collection();

                    collection.setCltImage(snapshot.child("cltImage").getValue().toString());
                    collection.setCltName(snapshot.child("cltName").getValue().toString());
                    collection.setCltNumber(snapshot.child("cltNumber").getValue().toString() + " $");

                    collectionArrayList.add(collection);

                }
                collectionAdapter = new CollectionAdapter(getContext(),collectionArrayList);
                rcvSeatCollection.setAdapter(collectionAdapter);
                collectionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}