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

public class MainActivity extends AppCompatActivity {

    Button buttonLogin;
    EditText inputNim, inputPassword;
    TextView textRegisterHere, textNotHaveAccount;
    LinearLayout main;

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

        main = findViewById(R.id.main);
        textNotHaveAccount = findViewById(R.id.textNotHaveAccount);
        buttonLogin = findViewById(R.id.buttonLogin);
        inputNim = findViewById(R.id.ediTextNIM);
        inputPassword = findViewById(R.id.ediTextPassword);
        textRegisterHere = findViewById(R.id.textRegisterHere);

         SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);

        // Cek apakah pengguna sudah login sebelumnya
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            // Jika sudah login, langsung arahkan ke halaman SuccessLogin
            Intent intent = new Intent(MainActivity.this, SuccessLogin.class);
            startActivity(intent);
            finish(); // Tutup activity saat ini agar pengguna tidak dapat kembali ke halaman login
        }


        if (sharedPreferences.getBoolean("darkMode", false)) {
            main.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
            textNotHaveAccount.setTextColor(ContextCompat.getColor(this,R.color.white));
        }

        buttonLogin.setOnClickListener(v -> {
            String nim = inputNim.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();
            String correctNim = sharedPreferences.getString("NIM", "");
            String correctPassword = sharedPreferences.getString("Password", "");

            if (nim.equals(correctNim) && password.equals(correctPassword)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.apply();

                Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, SuccessLogin.class);
                startActivity(intent);
                inputNim.setText("");
                inputPassword.setText("");
            } else {
                Toast.makeText(MainActivity.this, "NIM or password is incorrect", Toast.LENGTH_SHORT).show();
            }
        });

        textRegisterHere.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}