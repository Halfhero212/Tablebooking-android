package com.example.tablebookingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tablebookingapp.models.Restaurant;
import com.example.tablebookingapp.models.Review;
import com.example.tablebookingapp.adapters.ReviewListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class RestaurantDetailsFragment extends Fragment {

    private RecyclerView reviewListRecyclerView;
    private ReviewListAdapter adapter;
    private TextView restaurantNameTextView;
    private TextView restaurantCuisineTextView;
    private TextView restaurantLocationTextView;
    private TextView restaurantAveragePriceTextView;
    private TextView restaurantDescriptionTextView;
    private ImageView restaurantImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_details, container, false);

        restaurantNameTextView = view.findViewById(R.id.restaurant_name);
        restaurantCuisineTextView = view.findViewById(R.id.restaurant_cuisine);
        restaurantLocationTextView = view.findViewById(R.id.restaurant_location);
        restaurantAveragePriceTextView = view.findViewById(R.id.restaurant_avg_price);
        restaurantDescriptionTextView = view.findViewById(R.id.restaurant_description);
        restaurantImageView = view.findViewById(R.id.restaurant_image);

        Button proceedWithBookingButton = view.findViewById(R.id.proceed_with_booking_button);
        proceedWithBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_restaurantDetailsFragment_to_bookingFragment);
            }
        });

        reviewListRecyclerView = view.findViewById(R.id.review_list_recyclerview);

        String restaurantId = getRestaurantIdFromArguments();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        fetchRestaurantDetails(databaseReference, restaurantId);
        fetchReviews(databaseReference, restaurantId);

        return view;
    }

    private String getRestaurantIdFromArguments() {
        Bundle args = getArguments();
        if (args != null) {
            return args.getString("restaurantId");
        }
        return null;
    }

    private void fetchRestaurantDetails(DatabaseReference databaseReference, String restaurantId) {
        DatabaseReference restaurantRef = databaseReference.child("restaurants").child(restaurantId);
        restaurantRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);
                updateUI(restaurant);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });
    }

    private void fetchReviews(DatabaseReference databaseReference, String restaurantId) {
        DatabaseReference reviewsRef = databaseReference.child("restaurants").child(restaurantId).child("reviews");
        reviewsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Review> reviewList = new ArrayList<>();
                for (DataSnapshot reviewSnapshot : dataSnapshot.getChildren()) {
                    Review review = reviewSnapshot.getValue(Review.class);
                    reviewList.add(review);
                }
                setupRecyclerView(reviewList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });
    }

    private void updateUI(Restaurant restaurant) {
        restaurantNameTextView.setText(restaurant.getName());
        restaurantCuisineTextView.setText(restaurant.getCuisineType());
        restaurantLocationTextView.setText(restaurant.getLocation());
        restaurantAveragePriceTextView.setText(String.format("$%.2f", restaurant.getAveragePrice()));
        restaurantDescriptionTextView.setText(restaurant.getDescription());

        // Load image using Glide
        Glide.with(this).load(restaurant.getImageUrl()).into(restaurantImageView);
    }

    private void setupRecyclerView(List<Review> reviewList) {
        adapter = new ReviewListAdapter(getActivity(), reviewList);
        reviewListRecyclerView.setAdapter(adapter);
        reviewListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

} // Closing bracket for RestaurantDetailsFragment class
