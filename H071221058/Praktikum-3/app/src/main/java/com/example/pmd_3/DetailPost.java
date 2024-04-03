package com.example.pmd_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailPost extends AppCompatActivity {

    ImageView ivImage, ivPost, ivLike, ivBack;
    TextView tvName, tvCaption, tvUsername;
    boolean likedStatus = false; // Status awal untuk like

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        ivImage = findViewById(R.id.iv_image);
        ivPost = findViewById(R.id.iv_post);
        tvName = findViewById(R.id.tv_name);
        ivLike = findViewById(R.id.iv_like);
        tvCaption = findViewById(R.id.tv_caption);
        ivBack = findViewById(R.id.btn_back);
        tvUsername = findViewById(R.id.tv_username);

        // Mendapatkan data user dari intent
        User user = getIntent().getParcelableExtra("user");

        // Menampilkan data user pada tampilan detail post
        if (user != null) {
            ivImage.setImageResource(user.getImage());
            ivPost.setImageResource(user.getPost());
            tvName.setText(user.getName());
            tvUsername.setText(user.getName());
            tvCaption.setText(user.getCaption());
        }

        // Set OnClickListener untuk TextView 'Name' untuk membuka profil pengguna
        tvName.setOnClickListener(view -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("user", user); // Mengirim data user yang dipilih ke ProfileActivity
            startActivity(intent);
        });

        // Set OnClickListener untuk ImageView 'Like'
        ivLike.setOnClickListener(view -> {
            // Toggle status liked atau unliked
            likedStatus = !likedStatus;
            // Ubah gambar ImageView 'Like' sesuai dengan status baru
            if (likedStatus) {
                ivLike.setImageResource(R.drawable.like_pink); // Jika liked
            } else {
                ivLike.setImageResource(R.drawable.like); // Jika unliked
            }
        });

        ivBack.setOnClickListener(view -> {
            finish();
        });

        ivImage.setOnClickListener(view -> {
            Intent intent = new Intent(this, StoryActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });
    }
}
