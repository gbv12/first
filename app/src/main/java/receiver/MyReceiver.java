package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

import com.example.taobao.LoginActivity;
import com.example.taobao.RegisterActivity;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        Log.i("MainActivity","运行到这里");
        Object[] objects=(Object[]) intent.getExtras().get("pdus");
        for(Object obj: objects){
            SmsMessage smsMessage=SmsMessage.createFromPdu((byte[]) obj);
            String body=smsMessage.getMessageBody();
            String sender=smsMessage.getOriginatingAddress();
//            Log.i("MessageReceiver","body:"+body);
//            Log.i("MainActivity","sender:"+sender);
            if(body.contains("验证码")){
                int rel=body.indexOf("验证码");
//                String str=body.substring(rel+4,rel+8);
                String str="";
                int i=rel+4;
                while (body.charAt(i)<='9'&&body.charAt(i)>='0'){
                    str+=body.charAt(i);
                    i++;
                }
                if(!LoginActivity.flag){
                    LoginActivity.password.setText(str);
                }else if(!RegisterActivity.flag)
                RegisterActivity.et_code.setText(str);
            }
        }
    }
}