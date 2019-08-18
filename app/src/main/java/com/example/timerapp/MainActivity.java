package com.example.timerapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SeekBar seekBar;
    boolean active = false;
    CountDownTimer countDownTimer;
    Button stop;
    public void textviewupdate(int secondschoosen){
        int mint = (int) secondschoosen/60;
        int sec = secondschoosen%60;
        String seconds = Integer.toString(sec);

        if (sec <= 9){
            seconds="0"+sec;
        }
        textView.setText(Integer.toString(mint)+":"+seconds);

    }
    public  void resetTimer(){
        stop.setText("Go");
        textView.setText("0:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        seekBar.setEnabled(true);
        active= false;
    }


    public void gobutton(View view){
        if(active==false){

        active=true;
        seekBar.setEnabled(false);

        stop.setText("Stop");



        countDownTimer= new CountDownTimer(seekBar.getProgress() *1000+100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textviewupdate((int)millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);
                Log.i("info","timer has finished");
                mediaPlayer.start();


                resetTimer();

            }
        }.start();
    }
    else{


        resetTimer();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stop = findViewById(R.id.gobutton);



        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        textView= findViewById(R.id.timer);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                  textviewupdate(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}
