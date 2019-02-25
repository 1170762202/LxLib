package com.zlx.lxlib.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zlx.lxlib.util.LogUtil;


/**
 * Created by Hasee on 2017/1/5.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int version = 3; //数据库版本
    private SQLiteDatabase sqlitedatabase;
    private Cursor cursor;

    private static DatabaseHelper databaseHelper = null;


    public DatabaseHelper(Context context, String name) {
        this(context, name, version);
    }

    public DatabaseHelper(Context context, String name_database, int version_database) {
        super(context, name_database, null, version_database);
    }


    public static synchronized DatabaseHelper getInstance(Context context, String name) {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context, name);
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(DbP2PChat.create());
//        db.execSQL(DbGroupChat.create());
//        db.execSQL(com.mrdxm.xqdwl.database.bean.DbContact.create());
//        db.execSQL(DbConfig.create());
//        db.execSQL(DbSession.create());
//        db.execSQL(DbGroupInfo.create());
//        db.execSQL(DbCollect.create());
//        db.execSQL(DbDiscoveryPage.create());
//        LogUtil.e("onCreate DbUtil");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        LogUtil.e("onUpgrade DbUtil");
//        LogUtil.e("oldVersion" + oldVersion);
//        LogUtil.e("newVersion" + newVersion);
//        switch (newVersion){
//            case 3:
//                db.beginTransaction();
//                try {
//                    LogUtil.e("删除");
//                    db.execSQL("drop table if exists " + TABLE_P2P);
//                    db.execSQL("drop table if exists " + TABLE_GROUP);
//                    db.execSQL("drop table if exists " + Table_contacts);
//                    db.execSQL("drop table if exists " + Table_config);
//                    db.execSQL("drop table if exists " + TABLE_SESSION);
//                    db.execSQL("drop table if exists " + TABLE_GROUP_INFO);
//                    db.execSQL("drop table if exists " + TABLE_COLLECTION);
//                    db.execSQL("drop table if exists " + Table_discovery_page);
//                    LogUtil.e("创建");
//                    onCreate(db);
//                    db.setTransactionSuccessful();
//                } finally {
//                    db.endTransaction();
//                }
//                break;
//        }

    }
}
