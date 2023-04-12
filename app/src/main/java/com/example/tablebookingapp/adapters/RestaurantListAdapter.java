package com.example.tablebookingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tablebookingapp.R;
import com.example.tablebookingapp.models.Restaurant;

import java.util.List;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.ViewHolder> {

    private Context context;
    private List<Restaurant> restaurantList;
    private OnItemClickListener listener;

    // Interface for handling item clicks
    public interface OnItemClickListener {
        void onItemClick(String restaurantId);
    }

    // Constructor
    public RestaurantListAdapter(Context context, List<Restaurant> restaurantList, OnItemClickListener listener) {
        this.context = context;
        this.restaurantList = restaurantList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list_item, parent, false);
        return new ViewHolder(view, listener);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);

        // Bind data to the views
        holder.restaurantName.setText(restaurant.getName());
        holder.restaurantCuisine.setText(restaurant.getCuisineType());
        holder.restaurantLocation.setText(restaurant.getLocation());
        holder.restaurantRating.setText(String.format("%.1f", restaurant.getRating())); // Add this line
        holder.restaurantAvgPrice.setText(String.format("$%.2f", restaurant.getAveragePrice()));

        // Load image using Glide
        Glide.with(context).load(restaurant.getImageUrl()).into(holder.restaurantImage);

        // Set the click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                String restaurantId = restaurantList.get(currentPosition).getRestaurantId();
                listener.onItemClick(restaurantId);
            }
        });
    }


    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView restaurantImage;
        TextView restaurantName;
        TextView restaurantCuisine;
        TextView restaurantLocation;
        TextView restaurantRating; // Add this line
        TextView restaurantAvgPrice;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            restaurantImage = itemView.findViewById(R.id.restaurant_image);
            restaurantName = itemView.findViewById(R.id.restaurant_name);
            restaurantCuisine = itemView.findViewById(R.id.restaurant_cuisine);
            restaurantLocation = itemView.findViewById(R.id.restaurant_location);
            restaurantRating = itemView.findViewById(R.id.restaurant_rating); // Add this line
            restaurantAvgPrice = itemView.findViewById(R.id.restaurant_avg_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        String restaurantId = restaurantList.get(position).getRestaurantId();
                        listener.onItemClick(restaurantId);
                    }
                }
            });
        }
    }
}
