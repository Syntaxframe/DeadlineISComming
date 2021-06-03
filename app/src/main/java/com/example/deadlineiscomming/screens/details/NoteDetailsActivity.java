package com.example.deadlineiscomming.screens.details;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.deadlineiscomming.App;
import com.example.deadlineiscomming.R;
import com.example.deadlineiscomming.model.Note;
import com.example.deadlineiscomming.screens.main.MainActivity;

import static android.app.Notification.DEFAULT_ALL;
import static android.telephony.AvailableNetworkInfo.PRIORITY_HIGH;

public class NoteDetailsActivity extends AppCompatActivity {

    //класс в котором прописано окно сздания заметок

    // Объявим переменную в начале класса

    private int counter = 1;

    private static String CHANNEL_ID = "CHANNEL_ID";


    private static final String EXTRA_NOTE = "NoteDetailsActivity.EXTRA_NOTE";

    private Note note;

    private EditText editText;


    public static void start(Activity caller, Note note) {
        Intent intent = new Intent(caller, NoteDetailsActivity.class);
        if (note != null) {
            intent.putExtra(EXTRA_NOTE, note);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle(getString(R.string.note_details_title));

        editText = findViewById(R.id.text);

        if (getIntent().hasExtra(EXTRA_NOTE)) {
            note = getIntent().getParcelableExtra(EXTRA_NOTE);
            editText.setText(note.text);
        } else {
            note = new Note();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_save:
                //проверка на то, является ли поле текста пустым
                if (editText.getText().length() > 0) {
                    note.text = editText.getText().toString();
                    note.done = false;
                    note.timestamp = System.currentTimeMillis();
                    //тост при создании заметки
                    Toast.makeText(getApplicationContext(), "Заметка установлена", Toast.LENGTH_SHORT).show();
                    //уведомление при создании заметки
                    //
                    // Теперь у уведомлений будут новые идентификаторы
                    Intent notificationIntent = new Intent(NoteDetailsActivity.this, MainActivity.class);
                    PendingIntent contentIntent = PendingIntent.getActivity(NoteDetailsActivity.this,
                            0, notificationIntent,
                            PendingIntent.FLAG_CANCEL_CURRENT);
                    NotificationCompat.Builder builder =
                            new NotificationCompat.Builder(NoteDetailsActivity.this, CHANNEL_ID)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("Ваша заметка")
                                    .setContentText(note.text)
                                    .setPriority(NotificationCompat.PRIORITY_MAX)
                                    .setContentIntent(contentIntent)
                                    .setOngoing(true) ;

                    counter++;
                    NotificationManagerCompat notificationManager =
                            NotificationManagerCompat.from(NoteDetailsActivity.this);
                    notificationManager.notify(counter, builder.build());



                    if (getIntent().hasExtra(EXTRA_NOTE)) {
                        App.getInstance().getNoteDao().update(note);
                    } else {
                        App.getInstance().getNoteDao().insert(note);
                    }
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);

    }
    //оставил пока делал. Как говориться - работает не трогай!

}
