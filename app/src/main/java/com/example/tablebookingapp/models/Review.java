package com.example.tablebookingapp.models;

public class Review {
    private String id;
    private String restaurantId;
    private String userName;
    private String userImage;
    private double rating;
    private String comment;

    // No-argument constructor required for Firebase
    public Review() {
    }

    // Constructor
    public Review(String id, String restaurantId, String userName, String userImage, double rating, String comment) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.userName = userName;
        this.userImage = userImage;
        this.rating = rating;
        this.comment = comment;
    }

    // Getters and setters for each field
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
