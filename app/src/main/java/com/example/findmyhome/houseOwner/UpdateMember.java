package com.example.findmyhome.houseOwner;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findmyhome.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateMember extends AppCompatActivity {
    EditText et_memberName, et_memberId, et_memberJob, et_memberAge, et_memberRent, et_memberJoiningDate, et_memberPhoneNumber;
    Button btn_addMember;
    private ProgressDialog progressDialog;
    String ownerId,houseId,Name, memberId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_member);
        Intent intent = getIntent();
        houseId = intent.getStringExtra("houseId");
        String age = intent.getStringExtra("age");
        String job = intent.getStringExtra("job");
        String joiningDate = intent.getStringExtra("joiningDate");
        Name = intent.getStringExtra("name");
        String phoneNumber = intent.getStringExtra("phoneNumber");
        String rent = intent.getStringExtra("rent");
        ownerId = intent.getStringExtra("ownerId");
        memberId = intent.getStringExtra("memberId");

        et_memberAge = findViewById(R.id.et_updmemberAge);
        et_memberName = findViewById(R.id.et_updmemberName);
        et_memberJob = findViewById(R.id.et_updmemberJob);
        et_memberRent = findViewById(R.id.et_updmemberRent);
        et_memberJoiningDate = findViewById(R.id.et_updmemberJoiningDate);
        et_memberPhoneNumber = findViewById(R.id.et_updmemberPhoneNumber);
        btn_addMember = findViewById(R.id.btn_updaddMember);
        et_memberId = findViewById(R.id.et_updmemberId);
        et_memberId.setText(memberId);
        et_memberId.setEnabled(false);
        et_memberAge.setText(age);
        et_memberJob.setText(job);
        et_memberJoiningDate.setText(joiningDate);
        et_memberName.setText(Name);
        et_memberPhoneNumber.setText(phoneNumber);
        et_memberRent.setText(rent);

        progressDialog = new ProgressDialog(UpdateMember.this);

        btn_addMember.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Adding New Member");
                progressDialog.setTitle("Adding...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();


                String age = et_memberAge.getText().toString();
                String name = et_memberName.getText().toString();
                String job = et_memberJob.getText().toString();
                String rent = et_memberRent.getText().toString();
                String joiningDate = et_memberJoiningDate.getText().toString();
                String phoneNumber = et_memberPhoneNumber.getText().toString();

                createFood(age, name, job, rent, joiningDate, phoneNumber, ownerId, houseId);
                startActivity(new Intent(UpdateMember.this, SeeHouse.class));
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createFood(String age, String name, String job, String rent, String joiningDate, String phoneNumber, String ownerId, String houseId) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(RegisterOwner.MEMBERS).child(ownerId).child(houseId).child(memberId);
        HashMap hashMap = new HashMap();
        hashMap.put("houseId", houseId);
        hashMap.put("age", age);
        hashMap.put("name", name);
        hashMap.put("job", job);
        hashMap.put("rent", rent);
        hashMap.put("joiningDate", joiningDate);
        hashMap.put("phoneNumber", phoneNumber);
        hashMap.put("ownerId", ownerId);
        reference.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(UpdateMember.this, "Member Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateMember.this, "Member Updating Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}