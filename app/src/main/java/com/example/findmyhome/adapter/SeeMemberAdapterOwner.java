package com.example.findmyhome.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmyhome.MemberModel;
import com.example.findmyhome.R;
import com.example.findmyhome.houseOwner.UpdateHouse;
import com.example.findmyhome.houseOwner.UpdateMember;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SeeMemberAdapterOwner extends RecyclerView.Adapter<SeeMemberAdapterOwner.viewholder> {
    Context context;
    ArrayList<MemberModel> arrayList = new ArrayList<>();

    public SeeMemberAdapterOwner(Context context, ArrayList<MemberModel> houseModelArrayList) {
        this.context = context;
        this.arrayList = houseModelArrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.seemember, null, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        MemberModel model = arrayList.get(position);

        holder.tv_name.setText(model.getName());
        holder.tv_joiningDate.setText("Joinning Date: "+model.getJoiningDate());
        holder.tv_phoneNumber.setText("Phone Number: "+model.getPhoneNumber());
        holder.tv_age.setText("Age: "+model.getAge());
        holder.tv_job.setText("Job: "+model.getJob());
        holder.tv_rent.setText("Rent: $"+model.getRent());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("members")
                        .child(model.getOwnerId())
                        .child(model.getHouseId()).child(model.getMemberID()).removeValue();
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, UpdateMember.class);

                intent.putExtra("houseId", model.getHouseId());
                intent.putExtra("age", model.getAge());
                intent.putExtra("job", model.getJob());
                intent.putExtra("joiningDate", model.getJoiningDate());
                intent.putExtra("name", model.getName());
                intent.putExtra("phoneNumber", model.getPhoneNumber());
                intent.putExtra("rent", model.getRent());
                intent.putExtra("ownerId", model.getOwnerId());
                intent.putExtra("memberId", model.getMemberID());



                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class viewholder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_joiningDate, tv_phoneNumber, tv_age, tv_job, tv_rent;
        Button edit, delete;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_memberName);
            tv_joiningDate = itemView.findViewById(R.id.tv_memberJoiningDate);
            tv_phoneNumber = itemView.findViewById(R.id.tv_memberPhoneNumber);
            tv_job = itemView.findViewById(R.id.tv_memberJob);
            tv_age = itemView.findViewById(R.id.tv_memberAge);
            tv_rent = itemView.findViewById(R.id.tv_memberrent);
            edit = itemView.findViewById(R.id.editmember);
            delete = itemView.findViewById(R.id.deletemember);

        }
    }
}
