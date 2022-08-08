package com.example.findmyhome.houseOwner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.findmyhome.BookingModel;
import com.example.findmyhome.HouseModel;
import com.example.findmyhome.R;
import com.example.findmyhome.adapter.BookingAdapter;
import com.example.findmyhome.adapter.SeeHouseAdapterOwner;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SeeBookings extends AppCompatActivity {
    private RecyclerView rv_showAllFood;
    private BookingAdapter adapter;
    private ArrayList<BookingModel> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_bookings);
        rv_showAllFood = findViewById(R.id.recyclerView);
        rv_showAllFood.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SeeBookings.this);
        rv_showAllFood.setLayoutManager(linearLayoutManager);
        getAllArticle();
    }

    private void getAllArticle() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = firebaseUser.getUid();
        if (firebaseUser.getUid() != null) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Bookings").child(userId);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    mList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            BookingModel article = dataSnapshot.getValue(BookingModel.class);
                            mList.add(article);
                        }
                    adapter = new BookingAdapter(SeeBookings.this, mList);
                    rv_showAllFood.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}