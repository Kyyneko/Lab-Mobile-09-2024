package com.example.tugaspraktikum_4.fragment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.example.tugaspraktikum_4.MainActivity;
import com.example.tugaspraktikum_4.R;
import com.example.tugaspraktikum_4.User;

import java.io.IOException;

public class AddPostFragment extends Fragment {

    private ImageView addImage;
    private EditText addCaption;
    private Button btnShare;

    private ActivityResultLauncher<String> mGetContent;
    private Uri selectedImageUri; // Variabel untuk menyimpan URI gambar yang dipilih

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_post, container, false);

        // Inisialisasi view
        addImage = view.findViewById(R.id.addImagepost);
        addCaption = view.findViewById(R.id.addCaption);
        btnShare = view.findViewById(R.id.btn_share);

        // Inisialisasi ActivityResultLauncher untuk memilih konten dari galeri
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        selectedImageUri = uri; // Simpan URI gambar yang dipilih
                        displaySelectedImage(selectedImageUri);
                    }
                });

        // Set listener untuk tombol tambah gambar
        addImage.setOnClickListener(v -> openGallery());

        // Set listener untuk tombol "Share"
        btnShare.setOnClickListener(v -> shareImage());

        return view;
    }

    // Metode untuk membuka galeri
    private void openGallery() {
        mGetContent.launch("image/*"); // Membuka galeri untuk memilih gambar
    }

    // Metode untuk menampilkan gambar yang dipilih
    private void displaySelectedImage(Uri uri) {
        try {
            // Mengambil gambar dari URI dan menampilkannya di ImageView
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), uri);
            addImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metode untuk membagikan gambar ke HomeFragment
    private void shareImage() {
        // Membuat objek User baru dengan data yang dimasukkan
        User newUser = new User("Itachi", "Uchiha Itachi", addCaption.getText().toString(), R.drawable.itachi, selectedImageUri.toString());

        // Mengirim data ke HomeFragment menggunakan Bundle
        Bundle bundle = new Bundle();
        bundle.putParcelable("newUser", newUser);

        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);

        // Navigasi ke HomeFragment dan menghapus AddPostFragment dari back stack
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, homeFragment)
                .addToBackStack(null)
                .remove(this) // Menghapus AddPostFragment dari back stack
                .commit();

        // Mengatur item menu navigasi bawah ke menu "Home"
        ((MainActivity) requireActivity()).bottomNavigationView.setSelectedItemId(R.id.menu_home);
    }
}
