package com.example.pmd_8;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class CreateActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText titleEditText = findViewById(R.id.inputTitle);
        EditText descriptionEditText = findViewById(R.id.inputDescription);

        databaseHelper = new DatabaseHelper(this);
        Button createButton = findViewById(R.id.button_add);


        createButton.setOnClickListener(view -> {
            String title = titleEditText.getText().toString().trim();
            String description = descriptionEditText.getText().toString().trim();

            if (!title.isEmpty()) {
                Note note = new Note();
                note.setTitle(title);
                note.setDescription(description);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                note.setCreatedAt(sdf.format(new Date()));

                long result = databaseHelper.insertNote(note);

                if (result != -1) {
                    Toast.makeText(this, "Note berhasil disimpan", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(this, "Gagal menyimpan Note", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(CreateActivity.this, "Judul tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            new AlertDialog.Builder(this)
                    .setMessage("Yakin ingin kembali?")
                    .setPositiveButton("Ya", (dialog, which) -> finish())
                    .setNegativeButton("Tidak", null)
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
