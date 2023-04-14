package com.example.taobao;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SaveInfo {
    public  static boolean saveInfo(Context context,String account,String password){
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("num",account);
        editor.putString("pwd",password);
        editor.commit();
        return true;
    }
    public  static Map<String,String> getInfo(Context context){
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        String account=sp.getString("num",null);
        String password=sp.getString("pwd",null);
        Map<String,String> userMap=new HashMap<String,String>();
        userMap.put("account",account);
        userMap.put("password",password);
        return userMap;
    }
}
