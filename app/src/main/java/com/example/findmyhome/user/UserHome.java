package com.example.findmyhome.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.findmyhome.MainActivity;
import com.example.findmyhome.R;
import com.example.findmyhome.houseOwner.DashboardOwner;
import com.example.findmyhome.houseOwner.SeeHouse;
import com.google.firebase.auth.FirebaseAuth;

public class UserHome extends AppCompatActivity {

    Button property,logout,seebooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        property = findViewById(R.id.btn_UseeHouse);
        logout = findViewById(R.id.btn_Ulogout);
        seebooking = findViewById(R.id.btn_Useebookings);

        property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserHome.this, DashboardUser.class));
            }
        });
        seebooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserHome.this, TenantBookingList.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(UserHome.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}