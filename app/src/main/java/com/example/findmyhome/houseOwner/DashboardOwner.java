package com.example.findmyhome.houseOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.findmyhome.MainActivity;
import com.example.findmyhome.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardOwner extends AppCompatActivity {

    Button btn_addHouse, btn_seeHouse, btn_logout, btn_seebookings;

    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_owner);

        btn_addHouse = findViewById(R.id.btn_addHouse);
        btn_seeHouse = findViewById(R.id.btn_seeHouse);
        btn_logout = findViewById(R.id.btn_Ologout);
        btn_seebookings = findViewById(R.id.btn_seebookings);
        name = findViewById(R.id.Oname);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = firebaseUser.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Owner").child(userId).child("username").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText("Welcome back, "+snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(DashboardOwner.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_seeHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardOwner.this, SeeHouse.class));
            }
        });

        btn_addHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardOwner.this, AddHouse.class));
            }
        });
        btn_seebookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardOwner.this,SeeBookings.class));
            }
        });
    }
}