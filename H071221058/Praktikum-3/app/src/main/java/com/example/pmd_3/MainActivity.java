package com.example.pmd_3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView rv_users = findViewById(R.id.rv_users);
        RecyclerView rv_posts = findViewById(R.id.rv_posts);

        // Set Layout Manager
        rv_users.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv_posts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Fix kan Sized
        rv_users.setHasFixedSize(true);
        rv_posts.setHasFixedSize(true);


        // Make Adapter
        UserAdapter userAdapter = new UserAdapter(DataSource.users); // misalnya DataSource.users adalah data untuk rv_users
        PostAdapter postAdapter = new PostAdapter(DataSource.users); // misalnya DataSource.posts adalah data untuk rv_posts
        rv_users.setAdapter(userAdapter);
        rv_posts.setAdapter(postAdapter);
    }

}