package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import java.util.ArrayList;

public class addNewRestaurant extends AppCompatActivity {

    private static final String PREF_NAME = "RestaurantPrefs";
    private static final String KEY_RESTAURANTS = "restaurants";

    private EditText nameEditText;
    private EditText locationEditText;
    private EditText phoneEditText;
    private EditText descriptionEditText,ratingEditText;
    private Button saveButton;
    private SharedPreferences sharedPreferences;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_restaurant);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();

        nameEditText = findViewById(R.id.nameEditText);
        locationEditText = findViewById(R.id.locationEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        saveButton = findViewById(R.id.saveButton);
        ratingEditText = findViewById(R.id.ratingEditText);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRestaurant();
                Intent intent = new Intent(addNewRestaurant.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void saveRestaurant() {
        String name = nameEditText.getText().toString().trim();
        String location = locationEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String rating = ratingEditText.getText().toString().trim();

        if (!name.isEmpty() && !location.isEmpty() && !phone.isEmpty() && !description.isEmpty()) {
            restaurant restaurant = new restaurant(name, location, phone, description,rating);
            ArrayList<restaurant> restaurantList = loadRestaurants();
            restaurantList.add(restaurant);
            saveRestaurants(restaurantList);
            finish(); // Close the activity after saving
        } else {
            // Handle validation or display an error message
        }
    }
    private void saveRestaurants(ArrayList<restaurant> restaurants) {
        String json = gson.toJson(restaurants);
        sharedPreferences.edit().putString(KEY_RESTAURANTS, json).apply();
    }

    private ArrayList<restaurant> loadRestaurants() {
        String json = sharedPreferences.getString(KEY_RESTAURANTS, "");
        ArrayList<restaurant> restaurantList;
        if (!json.isEmpty()) {
            restaurantList = gson.fromJson(json, ArrayList.class);
        } else {
            restaurantList = new ArrayList<>();
        }
        return restaurantList;
    }
}