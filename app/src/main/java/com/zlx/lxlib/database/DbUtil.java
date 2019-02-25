package com.zlx.lxlib.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.zlx.lxlib.util.LogUtil;
import com.zlx.lxlib.util.SPUtil;

import java.util.ArrayList;

/**
 * Created by zlx on 2017/6/12.
 */

public class DbUtil {

    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public SQLiteDatabase getDb() {
        return db;
    }

    private static DbUtil instance;

    public static DbUtil getInstance() {
        if (instance == null) {
//            String sqliteName = (String) SPUtil.get(MyApp.instance, SPUtil.SQLITE_NAME, "");
//            LogUtil.e("*****************************" + sqliteName + "*********************");
//            instance = new DbUtil(MyApp.instance, sqliteName);
        }
        return instance;
    }

    public DbUtil(Context context, String name) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context, name);
        db = databaseHelper.getWritableDatabase();
    }

    public static void setNull() {
        if (instance != null) {
            instance = null;
        }
    }
}
