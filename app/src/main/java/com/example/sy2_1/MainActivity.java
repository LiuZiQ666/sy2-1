package com.example.sy2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Chronometer chronometer;
    private Button btn_start,btn_stop,btn_base;
    long stopTime;
    long base;
    private boolean flag = false;
    private boolean flag2 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        btn_start = findViewById(R.id.btnStart);
        btn_stop = findViewById(R.id.btnStop);
        btn_base = findViewById(R.id.btnReset);
        initView();
    }

    private void initView(){
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_base.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnStart:
                if(flag && flag2){
                    long t = SystemClock.elapsedRealtime()-stopTime;
                    chronometer.setBase(base+t);
                }
                if(!flag && flag2){
                    chronometer.setBase(SystemClock.elapsedRealtime());
                }
                chronometer.start();
                flag2 = false;
                break;
            case R.id.btnStop:
                if(!flag2){
                    stopTime = SystemClock.uptimeMillis();
                    chronometer.stop();
                    flag = true;
                    flag2 = true;
                    base = chronometer.getBase();
                }
                break;
            case R.id.btnReset:
                chronometer.setBase(SystemClock.elapsedRealtime());
                flag = false;
                break;
        }
    }

}
