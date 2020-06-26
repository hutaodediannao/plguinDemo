package com.app.plguinapp;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //ARouter初始化
        ARouter.init(this);

    }
}
