package com.example.deadlineiscomming;

import android.app.Application;

import androidx.room.Room;

import com.example.deadlineiscomming.data.AppDatabase;
import com.example.deadlineiscomming.data.NoteDao;

public class App extends Application {

    // что - то вроде ядра приложения(по другому сложно назвать)
    private AppDatabase database;
    private NoteDao noteDao;

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app-db-name")
                .allowMainThreadQueries()
                .build();

        noteDao = database.noteDao();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public NoteDao getNoteDao() {
        return noteDao;
    }

    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }
}