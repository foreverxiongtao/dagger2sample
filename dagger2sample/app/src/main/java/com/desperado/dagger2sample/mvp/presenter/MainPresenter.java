package com.desperado.dagger2sample.mvp.presenter;

import com.desperado.dagger2sample.global.RemoteAPI;
import com.desperado.dagger2sample.mvp.model.MainModel;
import com.desperado.dagger2sample.mvp.view.MainView;

/*
 *
 *
 * 版 权 :@Copyright 北京******科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/14  10:27
 *
 * 描 述 :主页面业务逻辑处理类
 *
 * 修订日期 :
 */
public class MainPresenter extends BasePresenter<MainView, MainModel> {

    public MainPresenter(RemoteAPI _remoteAPI) {
        super(_remoteAPI);
    }

    @Override
    public MainModel setUpModle() {
        return new MainModel(getRemoteAPI());
    }
}
