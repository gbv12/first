package service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;


import java.util.Timer;
import java.util.TimerTask;

public class TimeService2 extends Service {
    int time = 60;
    private Timer timer;
    private TimerTask task2;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent2, int flags, int startId) {
        timer = new Timer();
        task2 = new TimerTask() {
            @Override
            public void run() {
                time -= 1;
                Intent intent=new Intent();
                intent.setAction("service2");
                intent.putExtra("time2", time);
                sendBroadcast(intent);
                if (time <= 0) stopSelf();
            }
        };
        timer.schedule(task2, 0, 1000);
        return super.onStartCommand(intent2, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timer = null;
        task2 = null;
    }
}
