package com.example.tugas_pmd7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SuccessLogin extends AppCompatActivity {

    LinearLayout mainLayout;
    Switch switchDarkMode;
    Button buttonLogout;
    TextView nim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_success_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mainLayout = findViewById(R.id.main);
        switchDarkMode = findViewById(R.id.switchDarkMode);
        nim = findViewById(R.id.nim);
        buttonLogout = findViewById(R.id.buttonLogout);

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        boolean isDarkMode = sharedPreferences.getBoolean("darkMode", false);
        nim.setText(sharedPreferences.getString("NIM",""));

        // Set status dark mode sesuai yang disimpan
        switchDarkMode.setChecked(isDarkMode);

        if (isDarkMode) {
            mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
            switchDarkMode.setTextColor(ContextCompat.getColor(this, R.color.white));
        } else {
            mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.wheat));
            switchDarkMode.setTextColor(ContextCompat.getColor(this, R.color.black));
        }


        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Simpan status dark mode ke SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("darkMode", isChecked);
            editor.apply();

            if (isChecked) {
                mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
                switchDarkMode.setTextColor(ContextCompat.getColor(this, R.color.white));
            } else {
                mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.wheat));
                switchDarkMode.setTextColor(ContextCompat.getColor(this, R.color.black));
            }
        });

        buttonLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }


}
