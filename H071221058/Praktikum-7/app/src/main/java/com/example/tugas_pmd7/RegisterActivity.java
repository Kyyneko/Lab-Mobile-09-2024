package com.example.tugas_pmd7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    TextView textLoginHere,textAlreadyHaveAccount;
    LinearLayout main;
    EditText inputNim, inputPassword;
    Button buttonRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        main = findViewById(R.id.main);
        textAlreadyHaveAccount = findViewById(R.id.textAlreadyHaveAccount);
        textLoginHere = findViewById(R.id.textLoginHere);
        inputNim = findViewById(R.id.ediTextNIM);
        inputPassword = findViewById(R.id.ediTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);

        if (sharedPreferences.getBoolean("darkMode", false)) {
            main.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
            textAlreadyHaveAccount.setTextColor(ContextCompat.getColor(this,R.color.white));
        }

        buttonRegister.setOnClickListener(v -> {
            String nim = inputNim.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();

            // Periksa apakah field nim dan password tidak kosong
            if (nim.isEmpty() || password.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("NIM", nim);
                editor.putString("Password", password);
                boolean isRegistered = editor.commit();

                if (isRegistered) {
                    Toast.makeText(RegisterActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);

                    inputNim.setText("");
                    inputPassword.setText("");
                } else {
                    Toast.makeText(RegisterActivity.this, "Failed to register", Toast.LENGTH_SHORT).show();
                }
            }
        });


        textLoginHere.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }
}
