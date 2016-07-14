package com.desperado.dagger2sample.mvp.presenter;

import com.desperado.dagger2sample.global.RemoteAPI;
import com.desperado.dagger2sample.mvp.model.BaseModel;
import com.desperado.dagger2sample.mvp.view.BaseView;

/*
 *
 *
 * 版 权 :@Copyright 北京*****科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/13  19:46
 *
 * 描 述 :app业务处理基类  mvp presenter层
 *
 * 修订日期 :
 */
public abstract class BasePresenter<V extends BaseView, M extends BaseModel> implements PresenterHelper<V, M> {
    private V mView;
    private M mModel;
    private RemoteAPI mRemoteAPI;

    public BasePresenter(RemoteAPI _remoteAPI) {
        this.mRemoteAPI = _remoteAPI;
        initModel();
    }

    private void initModel() {
        mModel = setUpModle();
    }

    @Override
    public void attachView(V _view) {
        this.mView = _view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    public RemoteAPI getRemoteAPI() {
        return mRemoteAPI;
    }

    public V getView() {
        return mView;
    }

    public M getModel() {
        return mModel;
    }

}
