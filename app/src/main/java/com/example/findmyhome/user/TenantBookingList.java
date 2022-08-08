package com.example.findmyhome.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.findmyhome.BookingModel;
import com.example.findmyhome.R;
import com.example.findmyhome.TenantBookingModel;
import com.example.findmyhome.adapter.BookingAdapter;
import com.example.findmyhome.adapter.BookingTenantAdapter;
import com.example.findmyhome.houseOwner.SeeBookings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TenantBookingList extends AppCompatActivity {
    private RecyclerView rv_showAllFood;
    private BookingTenantAdapter adapter;
    private ArrayList<TenantBookingModel> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_booking_list);
        rv_showAllFood = findViewById(R.id.recyclerView);
        rv_showAllFood.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TenantBookingList.this);
        rv_showAllFood.setLayoutManager(linearLayoutManager);
        getAllArticle();
    }

    private void getAllArticle() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = firebaseUser.getUid();
        if (firebaseUser.getUid() != null) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User Bookings").child(firebaseUser.getUid());
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    mList.clear();
                    Log.d("firebase", String.valueOf(snapshot.getChildren()));
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        TenantBookingModel article = dataSnapshot.getValue(TenantBookingModel.class);
                        mList.add(article);
                    }
                    adapter = new BookingTenantAdapter(TenantBookingList.this, mList);
                    rv_showAllFood.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}