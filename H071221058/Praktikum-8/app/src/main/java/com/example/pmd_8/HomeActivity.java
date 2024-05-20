package com.example.pmd_8;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private View noDataView;
    private DatabaseHelper databaseHelper;

    final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    loadDataFromDatabase();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        FloatingActionButton btn_add = findViewById(R.id.button_add);
        btn_add.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CreateActivity.class);
            resultLauncher.launch(intent);
        });

        recyclerView = findViewById(R.id.recycler_view);
        noDataView = findViewById(R.id.no_data_view);
        SearchView searchView = findViewById(R.id.search_view);

        loadDataFromDatabase();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                recyclerView.setVisibility(View.GONE);
                noDataView.setVisibility(View.GONE);

                if (noteAdapter != null) {
                    noteAdapter.filterList(query);
                    updateNoDataView();
                }
                return true;
            }
        });


    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadDataFromDatabase() {
        recyclerView.setVisibility(View.GONE);
        noDataView.setVisibility(View.GONE);

        databaseHelper = new DatabaseHelper(this);

        List<Note> notes = databaseHelper.getAllNotes();

        if (noteAdapter == null) {
            noteAdapter = new NoteAdapter(notes, this, resultLauncher);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(noteAdapter);
        } else {
            noteAdapter.setNotes(notes);
            noteAdapter.notifyDataSetChanged();
        }

        updateNoDataView();
    }

    private void updateNoDataView() {
        if (noteAdapter.getItemCount() == 0) {
            recyclerView.setVisibility(View.GONE);
            noDataView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            noDataView.setVisibility(View.GONE);
        }
    }

}
