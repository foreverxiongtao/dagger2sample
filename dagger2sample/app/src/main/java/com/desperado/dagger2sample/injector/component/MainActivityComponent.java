package com.desperado.dagger2sample.injector.component;

import com.desperado.dagger2sample.injector.module.MainActivityModule;
import com.desperado.dagger2sample.injector.scope.ActivityScope;
import com.desperado.dagger2sample.ui.MainActivity;

import dagger.Component;

/*
 *
 *
 * 版 权 :@Copyright 北京****科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/14  11:15
 *
 * 描 述 :主页面注入器
 *
 * 修订日期 :
 */
@ActivityScope
@Component(dependencies = APPComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    /**将MainActivityMoudule提供的实体注入到maintivity中**/
    void inject(MainActivity _mainactivity);
}
