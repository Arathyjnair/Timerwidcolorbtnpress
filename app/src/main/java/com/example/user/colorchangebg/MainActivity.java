package com.example.user.colorchangebg;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    LinearLayout r1;
  //  String color;


    private TextView textTimer;
    private TextView  txtvw;

    private Button Bluebtn;
    long starttime = 0L;
    long timeBuffer = 0L;
    long timems = 0L;
    long updatet = 0L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler H = new Handler();
        Bluebtn = findViewById(R.id.mybutton);
        textTimer = findViewById(R.id.tym);

        findViewById(R.id.mybutton).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // button pressed


                    findViewById(R.id.rela2).setBackgroundColor(Color.BLUE);
                    starttime = SystemClock.uptimeMillis();
                    H.postDelayed(Trun, 0);


                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // button relased.

                    findViewById(R.id.rela2).setBackgroundColor(Color.GRAY);
                    timeBuffer = timeBuffer + timems;
                    H.removeCallbacks(Trun);


                }
                return false;
            }

            final Runnable Trun = new Runnable() {
                @Override
                public void run() {
                    timems = SystemClock.uptimeMillis() - starttime;
                    updatet = timeBuffer + timems;
                    int sec = (int) (updatet / 1000);
                    int min = sec / 60;
                    sec = sec % 1000;
                    int ms = (int) (updatet % 1000);
                    textTimer.setText("" + min + ":" + String.format("%2d", sec) + ":" + String.format("%3d", ms));
                    String Timer=textTimer.getText().toString();
                    SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                    SharedPreferences.Editor editor = app_preferences.edit();

                    editor.putString("Timerkeyy",Timer);
                    editor.commit();
                    H.postDelayed(this, 0);
                }
            };

        });


    }

    @Override
    protected void onStart() {
        Toast.makeText(this, "this is onStart", Toast.LENGTH_SHORT).show();
        super.onStart();
    }

    @Override
    protected void onStop() {
        Toast.makeText(this, "this is onStop", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onPause() {
        Toast.makeText(this, "this is onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onResume() {
        txtvw=findViewById(R.id.tym1);
        SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String ab=app_preferences.getString("Timerkeyy",null);
        txtvw.setText(ab);
        Toast.makeText(this, "this is onResume", Toast.LENGTH_SHORT).show();

        super.onResume();
    }
}
