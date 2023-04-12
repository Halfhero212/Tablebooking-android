package com.example.tablebookingapp.adapters;

import com.example.tablebookingapp.MenuItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.example.tablebookingapp.R;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private Context context;
    private List<MenuItem> menuItems;

    public MenuAdapter(Context context, List<MenuItem> menuItems) {
        this.context = context;
        this.menuItems = menuItems;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);
        holder.menuItemName.setText(menuItem.getName());
        holder.menuItemDescription.setText(menuItem.getDescription());
        holder.menuItemPrice.setText(String.format("$%.2f", menuItem.getPrice()));
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView menuItemName;
        TextView menuItemDescription;
        TextView menuItemPrice;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            menuItemName = itemView.findViewById(R.id.menu_item_name);
            menuItemDescription = itemView.findViewById(R.id.menu_item_description);
            menuItemPrice = itemView.findViewById(R.id.menu_item_price);
        }
    }
}
