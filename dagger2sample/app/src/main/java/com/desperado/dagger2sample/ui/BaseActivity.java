package com.desperado.dagger2sample.ui;

import android.app.Activity;
import android.os.Bundle;

import com.desperado.dagger2sample.GlobalApplication;
import com.desperado.dagger2sample.injector.component.APPComponent;
import com.desperado.dagger2sample.mvp.presenter.BasePresenter;

/*
 *
 *
 * 版 权 :@Copyright 北京*****科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/14  11:05
 *
 * 描 述 :
 *
 * 修订日期 :
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initcustomerActivityComponent(GlobalApplication.getGlobalApplication().getAppComponent());

    }
    /**
     * 为其他连接器提供全局的连接器依赖
     **/
    protected abstract void initcustomerActivityComponent(APPComponent _appcomponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getCurrentPersenter() != null) {
            getCurrentPersenter().unsubcrib();

        }
    }

    protected abstract BasePresenter getCurrentPersenter(); //获取当前的业务处理类

}
