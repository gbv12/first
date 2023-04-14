package com.example.taobao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class HomeMainActivity extends AppCompatActivity {
    private static final long DELAY = 3000;
    private TimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        final Intent localIntent=new Intent(this,MainActivity.class);//你要转向的Activity
        Timer timer=new Timer();
        TimerTask tast=new TimerTask() {
            @Override
            public void run(){
                startActivity(localIntent);//执行
            }
        };
        timer.schedule(tast,DELAY);//3秒后

    }
}