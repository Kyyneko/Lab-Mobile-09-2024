package com.example.pmd_3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Mendapatkan objek User dari intent
        User user = getIntent().getParcelableExtra("user");

        // Menetapkan nilai ke TextView sesuai dengan data dari objek User
        TextView tvUsername = findViewById(R.id.tv_username);
        TextView tvFollowers = findViewById(R.id.tv_followers);
        TextView tvFollowing = findViewById(R.id.tv_following);
        ImageView ivProfile = findViewById(R.id.iv_profile);
        ImageView ivPost = findViewById(R.id.iv_post);
        ImageView back = findViewById(R.id.btn_back);

        if (user != null) {
            tvUsername.setText(user.getName());
            tvFollowers.setText(user.getFollowers());
            tvFollowing.setText(user.getFollowing());
            ivProfile.setImageResource(user.getImage());
            ivPost.setImageResource(user.getPost());

        }

        ivPost.setOnClickListener(view -> {
            // Membuat intent untuk memulai FloatingActivity
            Intent intent = new Intent(ProfileActivity.this, DetailPost.class);

            // Meneruskan data pengguna ke FloatingActivity
            intent.putExtra("user", user);

            // Memulai FloatingActivity
            startActivity(intent);
        });

        back.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });

        ivProfile.setOnClickListener(view -> {
            Intent intent = new Intent(this, StoryActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });



    }
}
