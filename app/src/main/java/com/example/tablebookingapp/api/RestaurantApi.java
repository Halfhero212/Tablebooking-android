package com.example.tablebookingapp.api;


import com.example.tablebookingapp.models.Restaurant;
import com.example.tablebookingapp.models.Review;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestaurantApi {

    @GET("getRestaurantList")
    Call<List<Restaurant>> getRestaurantList();

    @GET("getRestaurants")
    Call<List<Restaurant>> getRestaurants();

    @GET("getRestaurant/{restaurantId}")
    Call<Restaurant> getRestaurant(@Path("restaurantId") String restaurantId);

    @GET("getReviews/{restaurantId}")
    Call<List<Review>> getReviews(@Path("restaurantId") String restaurantId);
}
