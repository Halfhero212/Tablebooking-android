package com.example.tablebookingapp.models;

import com.google.firebase.database.PropertyName;

import java.io.Serializable;
import java.util.Objects;

public class Restaurant implements Serializable {
    private String restaurantId;
    private String name;
    private String imageUrl;
    private String cuisineType;
    private String location;
    private double averagePrice;
    private String description;
    private float rating;

    // Default constructor
    public Restaurant() {
    }

    // Constructor
    public Restaurant(String restaurantId, String name, String imageUrl, String cuisineType, String location, double averagePrice, String description, float rating) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.cuisineType = cuisineType;
        this.location = location;
        this.averagePrice = averagePrice;
        this.description = description;
        this.rating = rating;
    }

    @PropertyName("restaurantId")
    public String getRestaurantId() {
        return restaurantId;
    }

    @PropertyName("restaurantId")
    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    @PropertyName("name")
    public String getName() {
        return name;
    }

    @PropertyName("name")
    public void setName(String name) {
        this.name = name;
    }

    @PropertyName("imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    @PropertyName("imageUrl")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @PropertyName("cuisineType")
    public String getCuisineType() {
        return cuisineType;
    }

    @PropertyName("cuisineType")
    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    @PropertyName("location")
    public String getLocation() {
        return location;
    }

    @PropertyName("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @PropertyName("averagePrice")
    public double getAveragePrice() {
        return averagePrice;
    }

    @PropertyName("averagePrice")
    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    @PropertyName("description")
    public String getDescription() {
        return description;
    }

    @PropertyName("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @PropertyName("rating")
    public float getRating() {
        return rating;
    }

    @PropertyName("rating")
    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Double.compare(that.averagePrice, averagePrice) == 0 &&
                Float.compare(that.rating, rating) == 0 &&
                Objects.equals(restaurantId, that.restaurantId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(cuisineType, that.cuisineType) &&
                Objects.equals(location, that.location) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, name, imageUrl, cuisineType, location, averagePrice, description, rating);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId='" + restaurantId + '\'' +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", cuisineType='" + cuisineType + '\'' +
                ", location='" + location + '\'' +
                ", averagePrice=" + averagePrice +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}
