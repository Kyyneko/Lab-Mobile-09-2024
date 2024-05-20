package com.example.pmd_8;

import android.content.DialogInterface;
import android.content.Intent;
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

public class EditActivity extends AppCompatActivity {

    private EditText inputTitle, inputDescription;
    private DatabaseHelper dbHelper;
    private int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputTitle = findViewById(R.id.inputTitle);
        inputDescription = findViewById(R.id.inputDescription);
        Button updateButton = findViewById(R.id.button_update);
        Button deleteButton = findViewById(R.id.button_delete);

        dbHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        if (intent.hasExtra("NOTE_ID")) {
            noteId = intent.getIntExtra("NOTE_ID", -1);

            Note note = dbHelper.getNoteById(noteId);
            if (note != null) {
                inputTitle.setText(note.getTitle());
                inputDescription.setText(note.getDescription());
            }
        }


        updateButton.setOnClickListener(v -> {
            String title = inputTitle.getText().toString().trim();
            String description = inputDescription.getText().toString().trim();
            String lastUpdated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());


            if (title.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Note note = new Note(noteId, title, description, null, lastUpdated);
            boolean result = dbHelper.updateNote(note);

            if (result) {
                Toast.makeText(this, "Note updated successfully", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
            }
        });

        deleteButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Apakah Anda yakin ingin menghapus Note ini?")
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            boolean deleted = dbHelper.deleteNote(noteId);
                            if (deleted) {
                                Toast.makeText(EditActivity.this, "Note berhasil dihapus", Toast.LENGTH_SHORT).show();
                                setResult(RESULT_OK);
                                finish();
                            } else {
                                Toast.makeText(EditActivity.this, "Gagal menghapus Note", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
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
