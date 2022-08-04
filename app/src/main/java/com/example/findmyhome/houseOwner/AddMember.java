package com.example.findmyhome.houseOwner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.findmyhome.MainActivity;
import com.example.findmyhome.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddMember extends AppCompatActivity {

    EditText et_memberName, et_memberID,et_memberJob, et_memberAge, et_memberRent, et_memberJoiningDate, et_memberPhoneNumber;
    Button btn_addMember;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        Intent intent = getIntent();
        String houseId = intent.getStringExtra("houseId");
        String noOfRoom = intent.getStringExtra("noOfRoom");
        String rentPerRoom = intent.getStringExtra("rentPerRoom");
        String houseDescription = intent.getStringExtra("houseDescription");
        String houseLocation = intent.getStringExtra("houseLocation");
        String houseImage = intent.getStringExtra("houseImage");
        String userId = intent.getStringExtra("userId");

        et_memberID = findViewById(R.id.et_memberId);
        et_memberAge = findViewById(R.id.et_memberAge);
        et_memberName = findViewById(R.id.et_memberName);
        et_memberJob = findViewById(R.id.et_memberJob);
        et_memberRent = findViewById(R.id.et_memberRent);
        et_memberJoiningDate = findViewById(R.id.et_memberJoiningDate);
        et_memberPhoneNumber = findViewById(R.id.et_memberPhoneNumber);
        btn_addMember = findViewById(R.id.btn_addMember);

        progressDialog = new ProgressDialog(AddMember.this);

        btn_addMember.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Adding New Member");
                progressDialog.setTitle("Adding...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                String memberid = et_memberID.getText().toString();
                String age = et_memberAge.getText().toString();
                String name = et_memberName.getText().toString();
                String job = et_memberJob.getText().toString();
                String rent = et_memberRent.getText().toString();
                String joiningDate = et_memberJoiningDate.getText().toString();
                String phoneNumber = et_memberPhoneNumber.getText().toString();

                createFood(age, name, job, rent, joiningDate, phoneNumber, userId, houseId,memberid);
                startActivity(new Intent(AddMember.this, SeeHouse.class));
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createFood(String age, String name, String job, String rent, String joiningDate, String phoneNumber, String ownerId, String houseId, String memberid) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = firebaseUser.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(RegisterOwner.MEMBERS).child(userId).child(houseId).child(memberid);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("houseId", houseId);
        hashMap.put("age", age);
        hashMap.put("name", name);
        hashMap.put("job", job);
        hashMap.put("rent", rent);
        hashMap.put("joiningDate", joiningDate);
        hashMap.put("phoneNumber", phoneNumber);
        hashMap.put("ownerId", ownerId);
        hashMap.put("memberId", memberid);
        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(AddMember.this, "Member Added Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddMember.this, "Member Added Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}