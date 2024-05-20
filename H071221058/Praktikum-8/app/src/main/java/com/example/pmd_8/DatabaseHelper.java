package com.example.pmd_8;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Notes.db";
    private static final Integer DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS notes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "description TEXT, " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "last_updated DATETIME DEFAULT CURRENT_TIMESTAMP ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @SuppressLint("Range")
    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notes", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex("id")));
                note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                note.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                note.setCreatedAt(cursor.getString(cursor.getColumnIndex("created_at")));
                note.setLastUpdated(cursor.getString(cursor.getColumnIndex("last_updated")));
                notes.add(note);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return notes;
    }

    public long insertNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", note.getTitle());
        values.put("description", note.getDescription());
        values.put("created_at", note.getCreatedAt());
        values.putNull("last_updated");

        long result = db.insert("notes", null, values);
        db.close();
        return result;
    }

    public boolean deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("notes", "id=?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }

    public boolean updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", note.getTitle());
        values.put("description", note.getDescription());
        values.put("last_updated", note.getLastUpdated());

        int result = db.update("notes", values, "id=?", new String[]{String.valueOf(note.getId())});
        db.close();
        return result > 0;
    }

    public Note getNoteById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("notes", null, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") Note note = new Note(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("description")),
                    cursor.getString(cursor.getColumnIndex("created_at")),
                    cursor.getString(cursor.getColumnIndex("last_updated"))
            );
            cursor.close();
            db.close();
            return note;
        } else {
            db.close();
            return null;
        }
    }
}
