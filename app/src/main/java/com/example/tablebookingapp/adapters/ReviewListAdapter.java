package com.example.tablebookingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tablebookingapp.R;
import com.example.tablebookingapp.models.Review;

import java.util.List;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder> {

    private final Context context;
    private final List<Review> reviewList;

    public ReviewListAdapter(Context context, List<Review> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = reviewList.get(position);

        // Load user image using a library like Glide or Picasso
        // For example: Glide.with(context).load(review.getUserImage()).into(holder.userImage);

        holder.userName.setText(review.getUserName());
        holder.rating.setRating((float) review.getRating());
        holder.comment.setText(review.getComment());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userImage;
        TextView userName;
        RatingBar rating;
        TextView comment;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.user_image);
            userName = itemView.findViewById(R.id.user_name);
            rating = itemView.findViewById(R.id.rating);
            comment = itemView.findViewById(R.id.comment);
        }
    }
}
