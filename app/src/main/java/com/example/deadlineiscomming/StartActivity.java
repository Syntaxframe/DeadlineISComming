package com.example.deadlineiscomming;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends Activity implements View.OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        //...

        //-------------------------------------------------------------------------------
        super.onCreate(savedInstanceState);
        startService(new Intent(this, MyService.class)); //вот єто вам нужно написать!!!!!!
        //-------------------------------------------------------------------------------
    }

    /** Обработка нажатия кнопок */
    public void onClick(View v) {
        //...
    }
    public void onBackPressed() {
        stopService(new Intent(this, MyService.class));
    }
}
