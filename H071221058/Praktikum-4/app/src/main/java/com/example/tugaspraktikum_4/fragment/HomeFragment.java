package com.example.tugaspraktikum_4.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvPost = view.findViewById(R.id.rv_posts);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get user data from DataSource
        userList = DataSource.getUserData();

        // Initialize RecyclerView and Adapter
        PostAdapter postAdapter = new PostAdapter(userList, requireContext());
        rvPost.setAdapter(postAdapter);

        // Set LayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        rvPost.setLayoutManager(layoutManager);

        Bundle bundle = getArguments();
        if (bundle != null) {
            User newUser = bundle.getParcelable("newUser");
            if (newUser != null) {
                // Add new user to the beginning of the list and notify adapter
                userList.add(0, newUser);
                postAdapter.notifyDataSetChanged();
            }
        }
    }
}
