package com.example.taobao;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import fragment.BuycarFragment;
import fragment.FirstPageFragment;
import fragment.MessageFragment;
import fragment.MicroTaoFragment;
import fragment.MyfileFragment;
import receiver.MyReceiver;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout microTaoLay;
    LinearLayout messageLay;
    LinearLayout buycarLay;
    LinearLayout myfileLay;
    LinearLayout firstPageLay;

    ImageView microTaoIcon;
    ImageView messageIcon;
    ImageView buycarIcon;
    ImageView myfileIcon;
    ImageView firstPageIcon;
    ImageView titleImage;

    TextView microTaoText;
    TextView messageText;
    TextView buycarText;
    TextView myfileText;


    FragmentManager manager;
    FragmentTransaction transaction;
    Fragment firFragment, microFragment, messageFragment, buycarFragment, myfileFragment;

    public static double all_cost;
    public double read_cost() throws IOException {
        FileInputStream fis = null;
        double cost;
        try {
            fis = openFileInput("all_cost.txt");
        } catch (FileNotFoundException e) {
            cost=0.0;
            return cost;
        }
        int length = fis.available();
        byte bytes[] = new byte[length];
        fis.read(bytes);
        fis.close();
        String content = new String(bytes, StandardCharsets.UTF_8);
        cost = Double.parseDouble(content);
        return  cost;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Fragment底部导航
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        firFragment = new FirstPageFragment();
        transaction.add(R.id.tb_fragment, firFragment);
        transaction.commit();
        initUI();

        requestPermissions(new String[] {Manifest.permission.RECEIVE_SMS},1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1){
            for(int i=0;i<permissions.length;i++){
                if(permissions[i].equals("android.permission.RECEIVE_SMS")&&grantResults[i]== PackageManager.PERMISSION_GRANTED){
                    System.out.println(1);
                    MyReceiver myReceiver=new MyReceiver();
                    IntentFilter intentFilter=new IntentFilter();
                    intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
                    intentFilter.setPriority(1000);
                    registerReceiver(myReceiver,intentFilter);
                }
            }

        }
    }


    private void initUI() {
        microTaoLay = findViewById(R.id.micro_tao_layout);
        messageLay = findViewById(R.id.message_layout);
        buycarLay = findViewById(R.id.buycar_layout);
        myfileLay = findViewById(R.id.myfile_layout);
        firstPageLay = findViewById(R.id.first_page_layout);
        firstPageLay.setVisibility(View.INVISIBLE);

        microTaoIcon = findViewById(R.id.microtao_icon);
        messageIcon = findViewById(R.id.message_icon);
        buycarIcon = findViewById(R.id.buycar_icon);
        myfileIcon = findViewById(R.id.myfile_icon);
        firstPageIcon = findViewById(R.id.first_page_icon);
        titleImage = findViewById(R.id.title_image);

        microTaoText = findViewById(R.id.microtao_text);
        messageText = findViewById(R.id.message_text);
        buycarText = findViewById(R.id.buycar_text);
        myfileText = findViewById(R.id.myfile_text);

        microTaoLay.setOnClickListener(this);
        messageLay.setOnClickListener(this);
        buycarLay.setOnClickListener(this);
        myfileLay.setOnClickListener(this);
        firstPageLay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        transaction = manager.beginTransaction();
        hideFragment(transaction);//隐藏之前的Fragment
        switch (v.getId()) {


            case R.id.micro_tao_layout:
                if(microFragment==null)
                {
                    microFragment = new MicroTaoFragment();
                    transaction.add(R.id.tb_fragment, microFragment);
                }else
                    transaction.show(microFragment);
                transaction.commit();
                microTaoIcon.setImageDrawable(getResources().getDrawable(R.drawable.weitao1));
                microTaoText.setTextColor(Color.RED);
                //显示首页布局，隐藏标题淘宝图片
                if (firstPageLay.getVisibility() != View.VISIBLE) {
                    firstPageLay.setVisibility(View.VISIBLE);
                    titleImage.setVisibility(View.INVISIBLE);
                }

                break;
            case R.id.message_layout:
                if(messageFragment==null)
                {
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.tb_fragment, messageFragment);
                }else
                    transaction.show(messageFragment);
                transaction.commit();
                messageIcon.setImageDrawable(getResources().getDrawable(R.drawable.xiaoxi));
                messageText.setTextColor(Color.RED);

                //显示首页布局，隐藏标题淘宝图片
                if (firstPageLay.getVisibility() != View.VISIBLE) {
                    firstPageLay.setVisibility(View.VISIBLE);
                    titleImage.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.buycar_layout:
                if(buycarFragment==null)
                {
                    buycarFragment = new BuycarFragment();
                    transaction.add(R.id.tb_fragment, buycarFragment);
                    BuycarFragment.fee=0.0;
                }else {
                    transaction.remove(buycarFragment);
                    buycarFragment = new BuycarFragment();
                    transaction.add(R.id.tb_fragment, buycarFragment);
                    BuycarFragment.fee=0.0;
                }
                transaction.commit();
                buycarIcon.setImageDrawable(getResources().getDrawable(R.drawable.gouwuche1));
                buycarText.setTextColor(Color.RED);

                //显示首页布局，隐藏标题淘宝图片
                if (firstPageLay.getVisibility() != View.VISIBLE) {
                    firstPageLay.setVisibility(View.VISIBLE);
                    titleImage.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.myfile_layout:
                if(myfileFragment==null)
                {
                    myfileFragment = new MyfileFragment();
                    transaction.add(R.id.tb_fragment, myfileFragment);
                }else
                    transaction.show(myfileFragment);

                transaction.commit();
                myfileIcon.setImageDrawable(getResources().getDrawable(R.drawable.wodelan));
                myfileText.setTextColor(Color.RED);

                //显示首页布局，隐藏标题淘宝图片
                if (firstPageLay.getVisibility() != View.VISIBLE) {
                    firstPageLay.setVisibility(View.VISIBLE);
                    titleImage.setVisibility(View.INVISIBLE);

                }
                break;

            case R.id.first_page_layout:
                if(firFragment==null)
                {
                    firFragment = new FirstPageFragment();
                    transaction.add(R.id.tb_fragment, firFragment);
                }else
                    transaction.show(firFragment);

                transaction.commit();
                firstPageLay.setVisibility(View.INVISIBLE);
                titleImage.setVisibility(View.VISIBLE);

            default:
                break;
        }
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (firFragment != null) {
            transaction.hide(firFragment);

        }
        if (microFragment != null) {
            transaction.hide(microFragment);
            microTaoIcon.setImageDrawable(getResources().getDrawable(R.drawable.weitao));
            microTaoText.setTextColor(Color.BLACK);

        }
        if (messageFragment != null) {
            transaction.hide(messageFragment);
            messageIcon.setImageDrawable(getResources().getDrawable(R.drawable.xiaoxi1));
            messageText.setTextColor(Color.BLACK);
        }
        if (buycarFragment != null) {
            transaction.hide(buycarFragment);
            buycarIcon.setImageDrawable(getResources().getDrawable(R.drawable.gouwuche));
            buycarText.setTextColor(Color.BLACK);
        }
        if (myfileFragment != null) {
            transaction.hide(myfileFragment);
            myfileIcon.setImageDrawable(getResources().getDrawable(R.drawable.wodehui));
            myfileText.setTextColor(Color.BLACK);
        }
    }
}