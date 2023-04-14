package com.example.taobao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

import fragment.MyfileFragment;
import receiver.MyBroadcastReceiver;
import receiver.MyBroadcastReceiver2;
import service.TimeService;
import service.TimeService2;

public class LoginActivity extends AppCompatActivity {
    private EditText name;
    private EditText number;
    public static EditText password;
    private Button btn;
    public static TextView code;
    public static  Boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MyfileFragment fragment = new MyfileFragment();
        Bundle bundle = new Bundle();
        number = (EditText) findViewById(R.id.number);
        password = (EditText) findViewById(R.id.password);
        btn = (Button) findViewById(R.id.button2);
        ImageView fh=(ImageView) findViewById(R.id.fanhui);
        TextView tv_register=(TextView) findViewById(R.id.register);

        code=(TextView) findViewById(R.id.code);
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    Intent intent=new Intent(LoginActivity.this, TimeService2.class);
                    startService(intent);
                    code.setText("重新发送(60s)");
                    flag=false;
                    Toast.makeText(getApplicationContext(),"发送成功",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"已发送，请稍后重试",Toast.LENGTH_SHORT);
                }

            }
        });
        MyBroadcastReceiver2 myBroadcastReceiver=new MyBroadcastReceiver2();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("service2");
        registerReceiver(myBroadcastReceiver,intentFilter);

        fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(getApplicationContext(),MyfileFragment.class);
//                startActivity(intent);
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        Map<String, String> userInfo = SaveInfo.getInfo(this);
        if (userInfo != null) {
            number.setText(userInfo.get("account"));
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.button2:
                        String num = number.getText().toString();
                        String pwd = password.getText().toString();
                        if (TextUtils.isEmpty(num)) {
                            Toast.makeText(getApplicationContext(), "请输入您的账号", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(pwd)) {
                            Toast.makeText(getApplicationContext(), "请输入您的密码", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                        boolean isSaveSuccess = SaveInfo.saveInfo(getApplicationContext(),num, pwd);
                        if (isSaveSuccess) {
                            Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "保存失败", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                Intent intent = new Intent();

                bundle.putString("number", number.getText().toString());
                intent.putExtras(bundle);
                setResult(2, intent);
                finish();
            }
        });


//    private void initView() {
//
//        btn.setOnClickListener((View.OnClickListener) this);
//    }

    }
}