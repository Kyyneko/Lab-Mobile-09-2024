package com.example.tugaspraktikum_4;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<User> userList;
    private Context context;

    // Constructor
    public PostAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        User user = userList.get(position);

        // Menampilkan data pada tampilan item
        holder.username.setText(user.getUsername());
        holder.caption.setText(user.getCaption());
        holder.pp.setImageResource(user.getImageProfile());
        // Mengecek tipe data imagePost
        if (user.getImagePost() instanceof Integer) {
            holder.post.setImageResource((Integer) user.getImagePost()); // Jika integer, maka setImageResource menggunakan integer
        } else {
            // Jika URI, gunakan kode untuk mengatur gambar dari URI
            Uri imagePostUri = Uri.parse((String) user.getImagePost());
            holder.post.setImageURI(imagePostUri);
        }

        holder.username.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProfileActivity.class);
            intent.putExtra("user", user);
            context.startActivity(intent);
        });

        holder.delete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Konfirmasi");
            builder.setMessage("Apakah Anda yakin ingin menghapus postingan ini?");
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int adapterPosition = holder.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        userList.remove(adapterPosition);
                        notifyItemRemoved(adapterPosition);
                    }
                }
            });
            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        });
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    // ViewHolder
    public static class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView pp, post, delete;
        TextView username, caption;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            pp = itemView.findViewById(R.id.pp);
            post = itemView.findViewById(R.id.post);
            username = itemView.findViewById(R.id.username);
            caption = itemView.findViewById(R.id.caption);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
