package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.taobao.LoginActivity;
import com.example.taobao.RegisterActivity;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int time = intent.getIntExtra("time", 60);
        if (time < 60 && time > 0) {
            RegisterActivity.flag = false;
        } else {
            RegisterActivity.flag = true;
        }
        if (time == 0) {
            RegisterActivity.tv_code.setText("重新发送");
        }
        RegisterActivity.tv_code.setText("重新发送(" + time + "s)");
    }
}
