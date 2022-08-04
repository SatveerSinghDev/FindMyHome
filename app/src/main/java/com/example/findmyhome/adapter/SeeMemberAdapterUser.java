package com.example.findmyhome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmyhome.MemberModel;
import com.example.findmyhome.R;

import java.util.ArrayList;

public class SeeMemberAdapterUser extends RecyclerView.Adapter<SeeMemberAdapterUser.viewholder>{
    Context context;
    ArrayList<MemberModel> arrayList = new ArrayList<>();

    public SeeMemberAdapterUser(Context context, ArrayList<MemberModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public SeeMemberAdapterUser.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.seemember2, null, false);
        return new  viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeeMemberAdapterUser.viewholder holder, int position) {
        MemberModel model = arrayList.get(position);

        holder.tv_name.setText(model.getName());
        holder.tv_joiningDate.setText("Joinning Date: "+model.getJoiningDate());
        holder.tv_phoneNumber.setText("Phone Number: "+model.getPhoneNumber());
        holder.tv_age.setText("Age: "+model.getAge());
        holder.tv_job.setText("Job: "+model.getJob());
        holder.tv_rent.setText("Rent: $"+model.getRent());
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
