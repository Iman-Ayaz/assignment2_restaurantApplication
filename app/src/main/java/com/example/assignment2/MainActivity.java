package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_NAME = "RestaurantPrefs";
    private static final String KEY_RESTAURANTS = "restaurants";

    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    RecyclerView.Adapter myAdapter;
    ArrayList<restaurant> restaurants;
    Button btnAdd,filterButton;
    EditText filterEditText;

    private ArrayList<restaurant> restaurantList;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvRestaurants);
        btnAdd = findViewById(R.id.btnAdd);
        filterButton=findViewById(R.id.btnFilter);
        filterEditText = findViewById(R.id.et_filterCriteria);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addNewRestaurant.class);
                startActivity(intent);
                finish();
            }
        });

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();

        restaurantList = loadRestaurants();

        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);


        myAdapter = new RestaurantAdapter(this,restaurantList);

        recyclerView.setAdapter(myAdapter);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterRestaurants();
            }
        });
    }

    private void filterRestaurants() {
        String filterCriteria = filterEditText.getText().toString().trim().toLowerCase();
        ArrayList<restaurant> filteredList = new ArrayList<>();

        for (restaurant restaurant : restaurantList) {
            // Check if any restaurant information matches the filter criteria
            if (restaurant.getName().toLowerCase().contains(filterCriteria) ||
                    restaurant.getLocation().toLowerCase().contains(filterCriteria) ||
                    restaurant.getDescription().toLowerCase().contains(filterCriteria) ||
                    String.valueOf(restaurant.getRating()).contains(filterCriteria)) {
                filteredList.add(restaurant);
            }
        }
        ((RestaurantAdapter) myAdapter).setRestaurantList(filteredList);
        myAdapter.notifyDataSetChanged();
    }


    private ArrayList<restaurant> loadRestaurants() {
        String json = sharedPreferences.getString(KEY_RESTAURANTS, "");
        if (json.isEmpty()) {
            return new ArrayList<>(); // Return an empty list if SharedPreferences is empty
        } else {
            Type type = new TypeToken<ArrayList<restaurant>>() {}.getType();
            return gson.fromJson(json, type);
        }
    }
}