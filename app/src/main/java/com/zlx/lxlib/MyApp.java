package com.zlx.lxlib;

import android.app.Application;

/**
 * @date: 2019\5\29 0029
 * @author: zlx
 * @email: 1170762202@qq.com
 * @description:
 */
public class MyApp extends Application {

    public static MyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
