package com.example.findmyhome.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.findmyhome.R;

public class HouseDetails extends AppCompatActivity {

    TextView tv_houseDesc;
    ImageView iv_houseImage;
    Button btn_viewMember, btn_viewLocation, btn_call, btn_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details2);

        Intent intent = getIntent();
        String houseId = intent.getStringExtra("houseId");
        String noOfRoom = intent.getStringExtra("noOfRoom");
        String rentPerRoom = intent.getStringExtra("rentPerRoom");
        String houseDescription = intent.getStringExtra("houseDescription");
        String houseLocation = intent.getStringExtra("houseLocation");
        String houseImage = intent.getStringExtra("houseImage");
        String userId = intent.getStringExtra("userId");

        tv_houseDesc = findViewById(R.id.tv_houseDesc);
        iv_houseImage = findViewById(R.id.iv_houseImage);

        btn_viewMember = findViewById(R.id.btn_viewMember);
        btn_viewLocation = findViewById(R.id.btn_viewLocation);
        btn_call = findViewById(R.id.btn_call);
        btn_message = findViewById(R.id.btn_message);

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8950562633"));
                startActivity(intent);
            }
        });

        btn_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", "8950562633");
                smsIntent.putExtra("sms_body","Message");
                startActivity(smsIntent);

            }
        });

        tv_houseDesc.setText("Number of rooms: "+noOfRoom+"\nRent per room: "+rentPerRoom+"\nLocation: "+houseLocation+"\nDescription: "+houseDescription);
        Glide.with(HouseDetails.this).load(houseImage).into(iv_houseImage);

        btn_viewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(HouseDetails.this, ViewLocation.class);
                intent1.putExtra("houseId", houseId);
                intent1.putExtra("noOfRoom", noOfRoom);
                intent1.putExtra("rentPerRoom", rentPerRoom);
                intent1.putExtra("houseDescription", houseDescription);
                intent1.putExtra("houseLocation", houseLocation);
                intent1.putExtra("houseImage", houseImage);
                intent1.putExtra("userId", userId);
                startActivity(intent1);
            }
        });
        btn_viewMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(HouseDetails.this, ViewMembers.class);
                intent1.putExtra("houseId", houseId);
                intent1.putExtra("noOfRoom", noOfRoom);
                intent1.putExtra("rentPerRoom", rentPerRoom);
                intent1.putExtra("houseDescription", houseDescription);
                intent1.putExtra("houseLocation", houseLocation);
                intent1.putExtra("houseImage", houseImage);
                intent1.putExtra("userId", userId);
                startActivity(intent1);
            }
        });

    }
}