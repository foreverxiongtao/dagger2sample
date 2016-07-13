package com.desperado.dagger2sample;

import android.app.Application;
import android.content.Context;

import com.desperado.dagger2sample.injector.component.APPComponent;

/*
 *
 *
 * 版 权 :@Copyright 北京xxxxx科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/13  17:58
 *
 * 描 述 :
 *
 * 修订日期 :
 */
public class GlobalApplication extends Application {
    private APPComponent mAppComponent;
    /****
     * 获取全局Context
     */
    private static Context mGlobalApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化全局的连接器
        mAppComponent = APPComponent.Initializer.init(this);
        mGlobalApplication = getApplicationContext();
    }


    public static Context getGlobalApplication() {
        return mGlobalApplication;
    }

    public APPComponent getAppComponent() {
        return mAppComponent;
    }
}
