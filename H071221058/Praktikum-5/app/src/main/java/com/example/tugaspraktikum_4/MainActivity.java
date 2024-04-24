package com.example.tugaspraktikum_4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.tugaspraktikum_4.fragment.AddPostFragment;
import com.example.tugaspraktikum_4.fragment.HomeFragment;
import com.example.tugaspraktikum_4.fragment.ProfileFragment;
import com.example.tugaspraktikum_4.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        // Set listener for bottom navigation view
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                // Replace fragment with HomeFragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
                return true;
            } else if (itemId == R.id.menu_post) {
                // Navigate to AddPostFragment and remove HomeFragment from back stack
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new AddPostFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)  // Add AddPostFragment to back stack
                        .commit();
                return true;
            } else if (itemId == R.id.menu_profile) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ProfileFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
                return true;
            } else if (itemId == R.id.menu_search) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new SearchFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
                return true;
            }
            return false;
        });


        // Set HomeFragment as default fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();
    }
}
