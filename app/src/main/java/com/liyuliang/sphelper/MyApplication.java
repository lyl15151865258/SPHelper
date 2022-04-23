package com.liyuliang.sphelper;

import android.app.Application;

import com.liyuliang.sphelper.contentprovider.SPHelper;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SPHelper.init(this);
    }
}
