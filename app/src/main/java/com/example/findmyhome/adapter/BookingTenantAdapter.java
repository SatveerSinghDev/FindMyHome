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
import com.example.findmyhome.TenantBookingModel;
import com.example.findmyhome.houseOwner.UpdateHouse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BookingTenantAdapter extends RecyclerView.Adapter<BookingTenantAdapter.viewholder>{
    Context context;
    ArrayList<TenantBookingModel> bookingModelArrayList = new ArrayList<>();

    public BookingTenantAdapter(Context context, ArrayList<TenantBookingModel> bookingModelArrayList) {
        this.context = context;
        this.bookingModelArrayList = bookingModelArrayList;
    }

    @NonNull
    @Override
    public BookingTenantAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bookinglisttenant, null, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingTenantAdapter.viewholder holder, int position) {
        TenantBookingModel model = bookingModelArrayList.get(position);
        holder.tv_location.setText("Address: " + model.getHouseLocation());
        holder.house_id.setText("House id: " + model.getHouseId());
        Glide.with(context).load(model.getHouseImage()).into(holder.iv_houseImage);
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                String userId = firebaseUser.getUid();
                FirebaseDatabase.getInstance().getReference().child("User Bookings")
                        .child(userId).removeValue();
                bookingModelArrayList.remove(position);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return bookingModelArrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView iv_houseImage;
        TextView house_id, tv_location;
        Button pay, cancel;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            iv_houseImage = itemView.findViewById(R.id.Timageview);
            tv_location = itemView.findViewById(R.id.Tlocation);
            house_id = itemView.findViewById(R.id.Thouseid);
            pay  = itemView.findViewById(R.id.Taccept);
            cancel = itemView.findViewById(R.id.Treject);
        }
    }
}
