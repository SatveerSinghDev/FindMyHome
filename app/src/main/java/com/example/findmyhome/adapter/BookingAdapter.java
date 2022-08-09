package com.example.findmyhome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.findmyhome.BookingModel;
import com.example.findmyhome.R;
import com.example.findmyhome.houseOwner.AddHouse;
import com.example.findmyhome.user.HouseDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.HashMap;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.viewholder> {
    Context context;
    ArrayList<BookingModel> bookingModelArrayList = new ArrayList<>();

    public BookingAdapter(Context context, ArrayList<BookingModel> bookingModelArrayList) {
        this.context = context;
        this.bookingModelArrayList = bookingModelArrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bookinglist, null, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        BookingModel model = bookingModelArrayList.get(position);

        holder.tv_location.setText("Address: " + model.getHouseLocation());
        holder.house_id.setText("House id: " + model.getHouseId());
        holder.username.setText("Email : " + model.getUser());
        Glide.with(context).load(model.getHouseImage()).into(holder.iv_houseImage);

        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                String userId = firebaseUser.getUid();
                FirebaseDatabase.getInstance().getReference().child("Bookings")
                        .child(userId)
                        .child(model.getUserid()).removeValue();
                bookingModelArrayList.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                String userId = firebaseUser.getUid();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User Bookings").child(model.getUserid()).child(firebaseUser.getUid());
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("houseId", model.getHouseId());
                hashMap.put("houseLocation", model.getHouseLocation());
                hashMap.put("houseImage", model.getHouseImage());
                hashMap.put("onwerid",firebaseUser.getUid());
                hashMap.put("status","Booked");
                reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            String userId = firebaseUser.getUid();
                            FirebaseDatabase.getInstance().getReference().child("Bookings")
                                    .child(userId)
                                    .child(model.getUserid()).removeValue();
                            bookingModelArrayList.remove(position);
                            notifyDataSetChanged();
                        } else {

                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookingModelArrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView iv_houseImage;
        TextView username, house_id, tv_location;
        Button accept, reject;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            iv_houseImage = itemView.findViewById(R.id.bimageview);
            tv_location = itemView.findViewById(R.id.blocation);
            username = itemView.findViewById(R.id.buser);
            house_id = itemView.findViewById(R.id.bhouseid);
            accept = itemView.findViewById(R.id.baccept);
            reject = itemView.findViewById(R.id.breject);
        }
    }
}