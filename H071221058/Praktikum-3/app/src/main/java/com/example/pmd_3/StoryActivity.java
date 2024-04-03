package com.example.pmd_3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StoryActivity extends AppCompatActivity {

    TextView tvUsername;
    ImageView ivStory,ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_story);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_story), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvUsername = findViewById(R.id.tv_username);
        ivStory = findViewById(R.id.iv_story);
        ivImage = findViewById(R.id.iv_image);


        User user = getIntent().getParcelableExtra("user");
        tvUsername.setText(user.getName());
        ivStory.setImageResource(user.getStory());
        ivImage.setImageResource(user.getImage());


        ivImage.setOnClickListener(view -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });

        tvUsername.setOnClickListener(view -> {
            Intent intent = new Intent(this,ProfileActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });
    }
}
