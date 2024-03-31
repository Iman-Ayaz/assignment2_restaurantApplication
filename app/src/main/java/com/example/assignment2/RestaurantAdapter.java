package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    ArrayList<restaurant> restaurants;
    public RestaurantAdapter(Context context,ArrayList<restaurant> restaurantArrayList)
    {
        restaurants = restaurantArrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvRatings,tvName,tvPhone,tvLocation,tvDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRatings = itemView.findViewById(R.id.tvRatings);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvDesc= itemView.findViewById(R.id.tvDesc);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(restaurants.get(position).getName());
        holder.tvPhone.setText(restaurants.get(position).getPhone());
        holder. tvRatings .setText(restaurants.get(position).getRating());
        holder.tvLocation.setText(restaurants.get(position).getLocation());
        holder.tvDesc.setText(restaurants.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public void setRestaurantList(ArrayList<restaurant> filteredList) {
        this.restaurants = filteredList;
    }
}
