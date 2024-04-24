package com.example.tugaspraktikum_4.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugaspraktikum_4.DataSource;
import com.example.tugaspraktikum_4.PostAdapter;
import com.example.tugaspraktikum_4.R;
import com.example.tugaspraktikum_4.User;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rvPost;
    private List<User> userList;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize RecyclerView and ProgressBar
        rvPost = view.findViewById(R.id.rv_posts);
        progressBar = view.findViewById(R.id.progressBar);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize RecyclerView and Adapter
        userList = DataSource.getUserData(); // Get user data
        PostAdapter postAdapter = new PostAdapter(userList, requireContext()); // Initialize adapter
        rvPost.setAdapter(postAdapter); // Set adapter to RecyclerView
        rvPost.setLayoutManager(new LinearLayoutManager(requireContext())); // Set layout manager

        // Check if there's new user data passed from other fragments
        Bundle bundle = getArguments();
        if (bundle != null) {
            User newUser = bundle.getParcelable("newUser"); // Get new user data
            if (newUser != null) {
                userList.add(0, newUser); // Add new user to the beginning of the list
                postAdapter.notifyDataSetChanged(); // Notify adapter about the data change
            }
        }

        // Show progress bar
        progressBar.setVisibility(View.VISIBLE);

        // Simulate data loading with a delay using Thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Simulate data loading process
                try {
                    Thread.sleep(1500); // Simulate loading for 1.5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Update UI on the main thread
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        // Hide progress bar after data loading is done
                        progressBar.setVisibility(View.GONE);
                        rvPost.setVisibility(View.VISIBLE); // Show RecyclerView
                    }
                });
            }
        }).start();
    }
}
