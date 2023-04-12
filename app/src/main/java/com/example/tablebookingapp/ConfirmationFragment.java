package com.example.tablebookingapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmationFragment extends Fragment {

    private String restaurantName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_confirmation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            restaurantName = getArguments().getString("restaurantName");
        }

        TextView restaurantNameTextView = view.findViewById(R.id.restaurantName);
        Button confirmButton = view.findViewById(R.id.confirmButton);
        Button cancelButton = view.findViewById(R.id.cancelButton);

        restaurantNameTextView.setText(restaurantName);

        final NavController navController = NavHostFragment.findNavController(this);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle booking confirmation
                navController.navigate(R.id.bookingFragment);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.restaurantListFragment);
            }
        });
    }
}
