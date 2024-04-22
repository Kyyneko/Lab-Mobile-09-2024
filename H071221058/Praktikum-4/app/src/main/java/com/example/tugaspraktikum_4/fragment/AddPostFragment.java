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
    private Uri selectedImageUri; // Deklarasi variabel selectedImageUri

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_post, container, false);

        addImage = view.findViewById(R.id.addImagepost);
        addCaption = view.findViewById(R.id.addCaption);
        btnShare = view.findViewById(R.id.btn_share);

        // Inisialisasi ActivityResultLauncher untuk memilih konten
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        selectedImageUri = uri; // Inisialisasi selectedImageUri saat gambar dipilih
                        displaySelectedImage(selectedImageUri);
                    }
                });

        addImage.setOnClickListener(v -> openGallery());
        btnShare.setOnClickListener(v -> shareImage()); // Panggil metode shareImage saat tombol "Share" ditekan

        return view;
    }

    private void openGallery() {
        mGetContent.launch("image/*");
    }

    private void displaySelectedImage(Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), uri);
            addImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Di AddPostFragment, setelah Anda mengirim data
    private void shareImage() {
        // Mengambil data yang akan dibagikan
        User newUser = new User("Freya", "Freyana Shifa Jayawardhana", addCaption.getText().toString(), R.drawable.pp1, selectedImageUri.toString());

        // Mengirim data ke HomeFragment
        Bundle bundle = new Bundle();
        bundle.putParcelable("newUser", newUser);

        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);

        // Navigate to HomeFragment and remove AddPostFragment from back stack
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, homeFragment)
                .addToBackStack(null)
                .remove(this) // Remove AddPostFragment from back stack
                .commit();

        ((MainActivity) requireActivity()).bottomNavigationView.setSelectedItemId(R.id.menu_home);
    }


}
