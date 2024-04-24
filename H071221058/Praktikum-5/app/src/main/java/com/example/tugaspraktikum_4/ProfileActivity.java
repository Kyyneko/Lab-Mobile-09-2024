package com.example.tugaspraktikum_4;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    ImageView pp;
    TextView username, name;
    ProgressBar progressBar;
    LinearLayout linear1;

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
        progressBar = findViewById(R.id.progressBar);
        linear1 = findViewById(R.id.linear1);

        // Mengambil data dari Intent
        User user = getIntent().getParcelableExtra("user");

        // Menyetel data ke tampilan
        if (user != null) {
            pp.setImageResource(user.getImageProfile());
            username.setText(user.getUsername());
            name.setText(user.getName());
        }

        // Memulai ProgressBar menggunakan Thread
        startProgressBar();
    }

    private void startProgressBar() {
        progressBar.setVisibility(View.VISIBLE);

        // Membuat Thread untuk menyimulasikan proses yang memakan waktu
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Menunggu selama 2 detik
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Menghentikan ProgressBar setelah selesai
                stopProgressBar();
            }
        }).start();
    }

    private void stopProgressBar() {
        // Menjalankan perubahan UI pada thread utama (UI thread)
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                linear1.setVisibility(View.VISIBLE);
            }
        });
    }
}
