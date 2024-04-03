package com.example.pmd_3;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private final ArrayList<User> users;  // ArrayList yang berisi data dari User

    // Constructor UserAdapter
    public UserAdapter(ArrayList<User> users) {
        this.users = users;
        Collections.shuffle(this.users);
    }

    // Class ViewHolder
    // Digunakan untuk menentukan bagaimana data ditampilkan ke dalam Layout Item.
    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Inisialisasi Variabel Untuk Digunakan Di Class
        private final TextView tvName;
        private final ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Pengambilan Item View Berdasarkan ID Yang Ada Di Layout
            ivImage = itemView.findViewById(R.id.iv_image);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    // Method onCreateViewHolder
    // Digunakan untuk membuat ViewHolder baru yang terhubung dengan layout item
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_item, parent, false);
        return new ViewHolder(view);
    }

    // Method onBindViewHolder
    // Digunakan untuk menetapkan data source ke dalam ViewHolder sesuai dengan posisinya.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);

        // Set Yang Akan Ditampilkan Di View ( Menampilkan Data di User )
        holder.ivImage.setImageResource(user.getImage());
        holder.tvName.setText(user.getName());

        holder.ivImage.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), StoryActivity.class);
            intent.putExtra("user", user);
            view.getContext().startActivity(intent);
        });
    }

    // Method getItemCount()
    // Digunakan untuk menetapkan ukuran dari jumlah data yang ingin ditampilkan.
    @Override
    public int getItemCount() {
        return users.size();
    }

}
