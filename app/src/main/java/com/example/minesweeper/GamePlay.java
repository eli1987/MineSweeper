package com.example.minesweeper;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class GamePlay extends Activity {
    public TextView numberOfMinesView;
    public static int counter = 0;
    public static boolean flag = false;
    private Thread t;
    private Handler handler = new Handler();
    public static int j = 0;
    public static int i = 0;
    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent mIntent = getIntent();
        int width = mIntent.getIntExtra("width", 0);
        int height = mIntent.getIntExtra("height", 0);
        int numberOfMines = mIntent.getIntExtra("numberOfMines", 0);

        Logic.getInstance().CreateGrid(this, width, height, numberOfMines);
        setContentView(R.layout.activity_game_play);

        numberOfMinesView = (TextView) findViewById(R.id.NumOfFlagsText);
        numberOfMinesView.setText("" + numberOfMines);
        //startTimer();

        tickOnMainThreadForever();


    }

    private void tickOnMainThreadForever() {
         runnable = new Runnable() {
            @Override
            public void run() {
                tick();
                handler.postDelayed(this, 1000);
            }
        };

        runnable.run();

    }


    private void tick() {
        i++;
        j = i;
        TextView Timetext = (TextView) findViewById(R.id.TimeText);
        Timetext.setText("" + i);
    }



@Override
    protected void onStop() {
        super.onStop();
         i = 0 ;
        handler.removeCallbacks(runnable);
    }


    @Override
    protected void onPause() {
        super.onPause();

        return;
    }

}



