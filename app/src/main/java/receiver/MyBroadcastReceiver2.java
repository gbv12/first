package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.taobao.LoginActivity;
import com.example.taobao.RegisterActivity;

public class MyBroadcastReceiver2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int time2=intent.getIntExtra("time2",60);
        if(time2 < 60 && time2 >0){

        }else{
            LoginActivity.flag = true;
        }
        if (time2==0){
            LoginActivity.code.setText("重新发送");
        }
        LoginActivity.code.setText("重新发送("+time2+"s)");
    }
}
