package com.android.zhiyufen.debugmenu.demo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class DomeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initARouter();
    }

    private void initARouter() {
        //if (isDebug()) {
        ARouter.openLog();
        ARouter.openDebug();
        //}
        ARouter.init(this);
    }
}
