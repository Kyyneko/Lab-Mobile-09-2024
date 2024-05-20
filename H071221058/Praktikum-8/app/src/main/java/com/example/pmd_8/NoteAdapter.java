package com.example.pmd_8;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> notes;
    private List<Note> notesFull;
    private final Context context;
    private final ActivityResultLauncher<Intent> resultLauncher;




    public NoteAdapter(List<Note> notes, Context context, ActivityResultLauncher<Intent> resultLauncher) {
        this.notes = notes;
        this.notesFull = new ArrayList<>(notes);
        this.context = context;
        this.resultLauncher = resultLauncher;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_note, parent, false);
        return new NoteViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.titleTextView.setText(note.getTitle());
        holder.descriptionTextView.setText(note.getDescription());

        String updatedAt = note.getLastUpdated();
        if (updatedAt != null && !updatedAt.isEmpty()) {
            holder.createdAtTextView.setText("Updated at " + updatedAt);
        } else {
            holder.createdAtTextView.setText("Created at " + note.getCreatedAt());
        }

        holder.editButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditActivity.class);
            intent.putExtra("NOTE_ID", note.getId());
            resultLauncher.launch(intent);
        });



    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterList(String query) {
        notes.clear();
        if (query.isEmpty()) {
            notes.addAll(notesFull);
        } else {
            query = query.toLowerCase();
            for (Note note : notesFull) {
                if (note.getTitle().toLowerCase().contains(query)) {
                    notes.add(note);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, descriptionTextView, createdAtTextView;
        ImageButton editButton;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title);
            descriptionTextView = itemView.findViewById(R.id.description);
            createdAtTextView = itemView.findViewById(R.id.time);
            editButton = itemView.findViewById(R.id.button_edit);
        }
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        this.notesFull = new ArrayList<>(notes);
    }

}
