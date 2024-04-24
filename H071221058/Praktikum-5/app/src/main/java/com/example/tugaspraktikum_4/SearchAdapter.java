package com.example.tugaspraktikum_4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements Filterable {

    private List<User> userList; // List untuk menyimpan data pengguna yang sedang ditampilkan
    private List<User> userListFull; // List untuk menyimpan salinan lengkap dari daftar asli
    private Context context;

    // Constructor untuk adapter
    public SearchAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
        this.userListFull = new ArrayList<>(userList); // Menginisialisasi salinan lengkap dari daftar
    }

    // Menginflate tata letak item dan membuat ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_search, parent, false);
        return new ViewHolder(view);
    }

    // Mengikat data ke tampilan item di RecyclerView
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);

        // Menetapkan data ke tampilan
        holder.username.setText(user.getUsername());
        holder.name.setText(user.getName());
        holder.pp.setImageResource(user.getImageProfile());

        // Menetapkan onClick listener untuk item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menangani klik item, mengirimkan data ke ProfileActivity
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("user", user);
                context.startActivity(intent);
            }
        });
    }

    // Mendapatkan jumlah item dalam daftar
    @Override
    public int getItemCount() {
        return userList.size();
    }

    // Kelas ViewHolder untuk menahan referensi ke tampilan item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView username, name;
        ImageView pp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            name = itemView.findViewById(R.id.name);
            pp = itemView.findViewById(R.id.pp);
        }
    }

    // Metode untuk mendapatkan filter untuk filter pencarian
    @Override
    public Filter getFilter() {
        return userFilter;
    }

    // Filter untuk pencarian
    private Filter userFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<User> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(userListFull); // Jika kueri pencarian kosong, tampilkan daftar lengkap
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                // Memfilter daftar pengguna berdasarkan kueri pencarian
                for (User user : userListFull) {
                    if (user.getUsername().toLowerCase().contains(filterPattern) || user.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(user); // Tambahkan pengguna ke daftar terfilter jika nama pengguna atau nama cocok dengan kueri pencarian
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        // Memperbarui daftar yang ditampilkan setelah proses filtering
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            userList.clear();
            userList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
