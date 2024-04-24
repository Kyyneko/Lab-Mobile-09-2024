package com.example.tugaspraktikum_4.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugaspraktikum_4.DataSource;
import com.example.tugaspraktikum_4.R;
import com.example.tugaspraktikum_4.SearchAdapter;
import com.example.tugaspraktikum_4.User;

import java.util.List;

public class SearchFragment extends Fragment {

    private RecyclerView rvSearch;
    private SearchView searchView;
    private ProgressBar progressBar;
    private SearchAdapter searchAdapter;
    private List<User> userList;

    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Mengembalikan tata letak untuk fragmen ini
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Mendapatkan referensi ke komponen UI
        rvSearch = view.findViewById(R.id.rv_search);
        searchView = view.findViewById(R.id.searchView);
        progressBar = view.findViewById(R.id.progressBar);

        // Mendapatkan data pengguna dan menginisialisasi adapter
        userList = DataSource.getUserData();
        searchAdapter = new SearchAdapter(userList, requireContext());

        // Mengatur adapter dan layout manager untuk RecyclerView
        rvSearch.setAdapter(searchAdapter);
        rvSearch.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Menginisialisasi handler untuk thread UI
        handler = new Handler(Looper.getMainLooper());

        // Mengatur listener untuk search view
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Menampilkan progress bar
                progressBar.setVisibility(View.VISIBLE);

                // Melakukan filter pada adapter
                searchAdapter.getFilter().filter(newText);

                // Memeriksa apakah kueri pencarian kosong
                if (newText.isEmpty()) {
                    // Sembunyikan progress bar dan RecyclerView secara langsung
                    progressBar.setVisibility(View.GONE);
                    rvSearch.setVisibility(View.GONE);
                } else {
                    // Sembunyikan progress bar setelah jeda
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            // Periksa apakah RecyclerView harus terlihat atau tidak
                            if (searchAdapter.getItemCount() > 0) {
                                rvSearch.setVisibility(View.VISIBLE);
                            } else {
                                rvSearch.setVisibility(View.GONE);
                            }
                        }
                    }, 1500); // Waktu jeda dalam milidetik
                }

                return true;
            }
        });

        // Mengatur listener untuk perubahan fokus pada search view
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // Periksa apakah search view kehilangan fokus dan kueri kosong
                if (!hasFocus && searchView.getQuery().toString().isEmpty()) {
                    // Sembunyikan RecyclerView
                    rvSearch.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        // Menghapus kueri pencarian saat fragmen di-pause
        if (searchView != null) {
            searchView.setQuery("", false);
        }
    }
}
