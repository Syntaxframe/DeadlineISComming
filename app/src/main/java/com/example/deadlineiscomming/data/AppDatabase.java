package com.example.deadlineiscomming.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.deadlineiscomming.model.Note;

//для бдшки
@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}