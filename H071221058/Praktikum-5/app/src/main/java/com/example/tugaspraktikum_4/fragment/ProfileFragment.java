package com.example.tugaspraktikum_4.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tugaspraktikum_4.R;

public class ProfileFragment extends Fragment {

    private ProgressBar progressBar;
    private Handler handler;
    private LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout untuk fragment ini
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        linearLayout = view.findViewById(R.id.linear1);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inisialisasi handler untuk mengontrol ProgressBar
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                // Perbarui progress bar
                int progress = msg.what;
                progressBar.setProgress(progress);

                // Hentikan ProgressBar saat mencapai 100%
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        };

        // Mulai ProgressBar
        startProgressBar();
    }

    private void startProgressBar() {
        progressBar.setVisibility(View.VISIBLE);

        // Buat Thread untuk mensimulasikan proses yang memakan waktu
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Tunggu selama 1.5 detik
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Hentikan ProgressBar setelah selesai
                handler.sendEmptyMessage(100);
            }
        }).start();
    }
}
