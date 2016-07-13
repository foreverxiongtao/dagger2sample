package com.desperado.dagger2sample.mvp.presenter;

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
public class BasePresenter<V extends BaseView> implements PresenterHelper<V> {
    protected V mView;

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

}
