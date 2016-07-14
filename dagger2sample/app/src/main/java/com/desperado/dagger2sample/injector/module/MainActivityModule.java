package com.desperado.dagger2sample.injector.module;

import com.desperado.dagger2sample.global.RemoteAPI;
import com.desperado.dagger2sample.injector.scope.ActivityScope;
import com.desperado.dagger2sample.mvp.presenter.MainPresenter;
import com.desperado.dagger2sample.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

/*
 *
 *
 * 版 权 :@Copyright 北京****科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/14  10:41
 *
 * 描 述 :主页面模块
 *
 * 修订日期 :
 */
@Module
public class MainActivityModule {
    private MainActivity mMainActivity;

    public MainActivityModule(MainActivity _mainactivity) {
        this.mMainActivity = _mainactivity;
    }

    @Provides
    @ActivityScope
    MainActivity provideMainActivity() {
        return mMainActivity;
    }

    @Provides
    @ActivityScope
    MainPresenter provideMainPresenter(MainActivity _mainactivity, RemoteAPI _remoteApi) {
        MainPresenter tempMainPresenter = new MainPresenter(_remoteApi);
        tempMainPresenter.attachView(_mainactivity);
        return tempMainPresenter;
    }
}
