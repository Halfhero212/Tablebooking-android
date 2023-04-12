package com.example.tablebookingapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.tablebookingapp.adapters.MenuAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {

    private RecyclerView menuRecyclerView;
    private MenuAdapter menuAdapter;
    private List<MenuItem> menuItems;
    private DatabaseReference menuDatabaseReference;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        menuRecyclerView = view.findViewById(R.id.menu_recycler_view);
        menuRecyclerView.setHasFixedSize(true);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        menuItems = new ArrayList<>();
        menuAdapter = new MenuAdapter(getContext(), menuItems); // Initialize menuAdapter here
        menuRecyclerView.setAdapter(menuAdapter); // Attach the adapter to RecyclerView here

        // Update the reference path to "menu_items/restaurant1"
        menuDatabaseReference = FirebaseDatabase.getInstance().getReference("menu_items/restaurant1");

        menuDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                menuItems.clear();

                for (DataSnapshot menuSnapshot : dataSnapshot.getChildren()) {
                    MenuItem menuItem = menuSnapshot.getValue(MenuItem.class);
                    menuItems.add(menuItem);
                }

                menuAdapter.notifyDataSetChanged(); // Notify the adapter that data has been updated
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
            }
        });

        return view;
    }
}
