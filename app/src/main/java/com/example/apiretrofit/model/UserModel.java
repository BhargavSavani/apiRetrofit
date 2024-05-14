package com.example.apiretrofit.model;

public class UserModel {
    Integer id;
    Double price;
    String title;
    String description, category, image;
    Rating rating;


    public static class Rating {
        float rate;
        int count;
        public float getRate() {
            return rate;
        }
        public int getCount() {
            return count;
        }
    }

    public UserModel(Integer id, Double price, String title, String description, String category, String image, Rating rating) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.description = description;
        this.category = category;
        this.image = image;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
