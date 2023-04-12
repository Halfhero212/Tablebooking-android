package com.example.tablebookingapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tablebookingapp.adapters.RestaurantListAdapter;
import com.example.tablebookingapp.models.Restaurant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class RestaurantListFragment extends Fragment implements RestaurantListAdapter.OnItemClickListener {

    private RecyclerView restaurantListRecyclerView;
    private RestaurantListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        restaurantListRecyclerView = view.findViewById(R.id.restaurant_list_recyclerview);

        fetchRestaurants();
        return view;
    }

    private void fetchRestaurants() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("restaurants");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Restaurant> restaurantList = new ArrayList<>();
                for (DataSnapshot restaurantSnapshot : dataSnapshot.getChildren()) {
                    Restaurant restaurant = restaurantSnapshot.getValue(Restaurant.class);
                    if (restaurant != null) {
                        restaurant.setRestaurantId(restaurantSnapshot.getKey());
                        restaurantList.add(restaurant);
                        Log.d("FetchedRestaurant", restaurant.toString()); // Add this line to log the fetched restaurant
                    }
                }
                setupRecyclerView(restaurantList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FetchError", "Error fetching restaurants: ", databaseError.toException()); // Add this line to log the error
            }
        });
    }


    private void setupRecyclerView(List<Restaurant> restaurantList) {
        adapter = new RestaurantListAdapter(requireContext(), restaurantList, this);
        restaurantListRecyclerView.setAdapter(adapter);
        restaurantListRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public void onItemClick(String restaurantId) {
        Bundle bundle = new Bundle();
        bundle.putString("restaurantId", restaurantId);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_restaurantListFragment_to_restaurantDetailsFragment, bundle);
    }
}
