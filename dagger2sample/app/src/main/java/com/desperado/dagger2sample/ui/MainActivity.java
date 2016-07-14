package com.desperado.dagger2sample.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.desperado.dagger2sample.R;
import com.desperado.dagger2sample.injector.component.APPComponent;
import com.desperado.dagger2sample.injector.component.DaggerMainActivityComponent;
import com.desperado.dagger2sample.injector.module.MainActivityModule;
import com.desperado.dagger2sample.mvp.presenter.MainPresenter;
import com.desperado.dagger2sample.mvp.view.MainView;

import javax.inject.Inject;

/*
 *
 *
 * 版 权 :@Copyright 北京******科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/13  19:19
 *
 * 描 述 :主页面
 *
 * 修订日期 :
 */
public class MainActivity extends BaseActivity implements MainView {
    @Inject
    MainPresenter mMainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initcustomerActivityComponent(APPComponent _appcomponent) {
        DaggerMainActivityComponent.builder().aPPComponent(_appcomponent).mainActivityModule(new MainActivityModule(this)).build().inject(this);
    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showErrorMsg(String msg, View.OnClickListener onClickListener) {

    }

    @Override
    public void showEmptyMsg(String msg, View.OnClickListener onClickListener) {

    }

    @Override
    public void showNetError(View.OnClickListener onClickListener) {

    }
}
