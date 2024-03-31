package com.example.assignment2;

public class restaurant {
    String name;
    String location;
    String phone;
    String description;
    String rating;

    public restaurant() {
    }

    public restaurant(String name, String location, String phone, String description, String rating) {
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.description = description;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
