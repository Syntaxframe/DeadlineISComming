package com.example.deadlineiscomming.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.deadlineiscomming.App;
import com.example.deadlineiscomming.model.Note;

import java.util.List;

// класс для связи частей бд-шки
public class MainViewModel extends ViewModel {
    private LiveData<List<Note>> noteLiveData = App.getInstance().getNoteDao().getAllLiveData();

    public LiveData<List<Note>> getNoteLiveData() {
        return noteLiveData;
    }
}
