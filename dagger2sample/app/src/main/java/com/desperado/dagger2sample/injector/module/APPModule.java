package com.desperado.dagger2sample.injector.module;
import com.desperado.dagger2sample.GlobalApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
/*
 *
 *
 * 版 权 :@Copyright 北京xxxxxx科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/13  18:03
 *
 * 描 述 :全局模块，提高一些全局的常用的Context,Config等其他的
 *
 * 修订日期 :
 *
 *
 */
@Module
public class APPModule {
    private GlobalApplication mApplication;

    public APPModule(GlobalApplication _application) {
        this.mApplication = _application;
    }

    @Singleton
    @Provides
    public GlobalApplication provideApplication() {
        return mApplication;
    }
}
