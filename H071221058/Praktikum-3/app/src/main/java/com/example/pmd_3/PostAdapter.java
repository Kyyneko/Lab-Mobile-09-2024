package com.example.pmd_3;

import static com.example.pmd_3.DataSource.users;

import android.annotation.SuppressLint;
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

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private final ArrayList<User> users;
    private boolean[] likedStatus;  // Array untuk melacak status like atau unlike setiap postingan


    // Constructor PostAdapter
    public PostAdapter(ArrayList<User> users) {
        // Mengacak urutan elemen di dalam ArrayList
        this.users = new ArrayList<>(users);
        Collections.shuffle(this.users);
        likedStatus = new boolean[this.users.size()]; // Inisialisasi array likedStatus
    }

    // Class ViewHolder
    // Digunakan untuk menentukan bagaimana data postingan ditampilkan ke dalam Layout Item.
    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Inisialisasi Variabel Untuk Digunakan Di Class
        private final TextView tvName,tvCaption;
        private final ImageView ivImage, ivPost, ivLike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Pengambilan Item View Berdasarkan ID Yang Ada Di Layout
            ivImage = itemView.findViewById(R.id.iv_image);
            ivPost = itemView.findViewById(R.id.iv_post);
            tvName = itemView.findViewById(R.id.tv_name);
            ivLike = itemView.findViewById(R.id.iv_like);
            tvCaption = itemView.findViewById(R.id.tv_caption);
        }
    }



    // Method onCreateViewHolder
    // Digunakan untuk membuat ViewHolder baru yang terhubung dengan layout item postingan
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item, parent, false);
        return new ViewHolder(view);
    }

    // Method onBindViewHolder
    // Digunakan untuk menetapkan data source ke dalam ViewHolder sesuai dengan posisinya.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);

        // Set Yang Akan Ditampilkan Di View ( Menampilkan Data di Postingan )
        holder.ivImage.setImageResource(user.getImage());
        holder.ivPost.setImageResource(user.getPost());
        holder.tvName.setText(user.getName());
        holder.tvCaption.setText(user.getCaption());

        // Set OnClickListener untuk ImageView 'Like'
        holder.ivLike.setOnClickListener(view -> {
            likedStatus[position] = !likedStatus[position];
            if(likedStatus[position]) {
                holder.ivLike.setImageResource(R.drawable.like_pink);
            } else {
                holder.ivLike.setImageResource(R.drawable.like);
            }
        });

        // Set OnClickListener untuk TextView 'Name' (nama pengguna)
        holder.tvName.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ProfileActivity.class);
            intent.putExtra("user",user);
            view.getContext().startActivity(intent);
        });

        holder.ivImage.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), StoryActivity.class);
            intent.putExtra("user",user);
            view.getContext().startActivity(intent);
        });
    }

    // Method getItemCount()
    // Digunakan untuk menetapkan ukuran dari jumlah data postingan yang ingin ditampilkan.
    @Override
    public int getItemCount() {
        return users.size();
    }


}
