package com.example.findmyhome.houseOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.findmyhome.MainActivity;
import com.example.findmyhome.R;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardOwner extends AppCompatActivity {

    Button btn_addHouse, btn_seeHouse, btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_owner);

        btn_addHouse = findViewById(R.id.btn_addHouse);
        btn_seeHouse = findViewById(R.id.btn_seeHouse);
        btn_logout = findViewById(R.id.btn_Ologout);

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
    }
}