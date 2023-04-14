package com.example.taobao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import receiver.MyBroadcastReceiver;
import service.TimeService;

public class RegisterActivity extends AppCompatActivity {
    public static EditText et_code;
    public static TextView tv_code;
    public static Boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);
        et_code=(EditText) findViewById(R.id.et_code);
        tv_code=(TextView) findViewById(R.id.ob_code);
        tv_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    Intent intent=new Intent(RegisterActivity.this, TimeService.class);
                    startService(intent);
                    tv_code.setText("重新发送(60s)");
                    flag=false;
                    Toast.makeText(getApplicationContext(),"发送成功",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"已发送，请稍后重试",Toast.LENGTH_SHORT);
                }
            }
        });
        MyBroadcastReceiver myBroadcastReceiver=new MyBroadcastReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("service");
        registerReceiver(myBroadcastReceiver,intentFilter);
    }
}