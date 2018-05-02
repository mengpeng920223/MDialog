package com.mengpeng.mdialog;

import android.app.Application;

/**
 * 作者: 孟鹏
 * 时间: 2018/5/2 - 15:03.
 * 作用:
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MDialog.init(this);
    }
}
