package service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class TimeService extends Service {
    public TimeService() {
    }

    int time = 60;
    int time2=60;
    private Timer timer;
    private TimerTask task;
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
    public int onStartCommand(Intent intent1, int flags, int startId) {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                time -= 1;
                Intent intent=new Intent();
                intent.setAction("service");
                intent.putExtra("time", time);
                sendBroadcast(intent);
                if (time <= 0) stopSelf();
            }
        };
        timer.schedule(task, 0, 1000);
        return super.onStartCommand(intent1, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timer = null;
        task = null;
    }
}