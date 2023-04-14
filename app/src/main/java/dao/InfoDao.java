package dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Sampler;

import java.util.ArrayList;
import java.util.List;

import bean.Info;

public class InfoDao{
    private MyHelper helper;
    public InfoDao(Context context){
        helper =new MyHelper(context);
    }
    public void insert(Info info){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("dianming",info.getDianming());
        values.put("mingzi",info.getMingzi());
        values.put("price",info.getPrice());
        values.put("number",info.getNumber());
        values.put("shangpin",info.getShangpin());
        values.put("flag",info.isFlag());
        values.put("cost",info.getPrice()*info.getNumber());
        long id=db.insert("ShopCar",null,values);
        info.setId(id);
        db.close();
    }
    public int delete(long id){
        SQLiteDatabase db=helper.getWritableDatabase();
        int count=db.delete("ShopCar","_id=?",new String[]{id+""});

        db.close();
        return count;
    }
    public int update(Info info){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("dianming",info.getDianming());
        values.put("mingzi",info.getMingzi());
        values.put("price",info.getPrice());
        values.put("number",info.getNumber());
        values.put("shangpin",info.getShangpin());
        values.put("flag",info.isFlag());
        values.put("cost",info.getPrice()*info.getNumber());
        int count=db. update("ShopCar",values,"_id=?",new String[]{info.getID()+""});
        db.close();
        return count;
    }
    public List<Info> queryAll(){
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor c=db.query("ShopCar",null,null,null,null,null,
               null);
        List<Info> list=new ArrayList<Info>();
        while(c.moveToNext()){
            @SuppressLint("Range") long id=c.getLong(c.getColumnIndex("_id"));
            String dm=c.getString(1);
            String mz=c.getString(2);
            double price=c.getDouble(3);
            Integer num=c.getInt(4);
            Long sp=c.getLong(5);
            int fg=c.getInt(6);
            list.add(new Info(id,dm,mz,price,num,sp,fg));
        }
        c.close();
        db.close();
        return list;
    }
}
