package com.example.tugaspraktikum_4;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    ImageView pp;

    TextView username, name;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pp = findViewById(R.id.pp);
        username = findViewById(R.id.username);
        name = findViewById(R.id.name);


        // Mengambil data dari Intent
        User user = getIntent().getParcelableExtra("user");

        // Menyetel data ke tampilan
        if (user != null) {
            pp.setImageResource(user.getImageProfile());
            username.setText(user.getUsername());
            name.setText(user.getName());
        }
    }
}