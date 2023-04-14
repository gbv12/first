package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context) {
        super(context, "itcast.db", null, 2);
    }

    public void onCreate(SQLiteDatabase db) {
        System.out.println("onCreate");
        db.execSQL("CREATE TABLE ShopCar(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "dianming VARCHAR(30)," + // 店名列
                "mingzi VARCHAR(30),"+  // 商品名列
                "price double,number INTEGER,shangpin long,flag int,cost double)"); //价格、数量、图片
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("onUpgrade");
    }
}
