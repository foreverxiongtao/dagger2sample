package com.desperado.dagger2sample.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.desperado.dagger2sample.R;
import com.desperado.dagger2sample.entity.WeatherInfo;
import com.desperado.dagger2sample.injector.component.APPComponent;
import com.desperado.dagger2sample.injector.component.DaggerMainActivityComponent;
import com.desperado.dagger2sample.injector.module.MainActivityModule;
import com.desperado.dagger2sample.mvp.presenter.BasePresenter;
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
    private TextView mTv_main_weather_desc;
    private Button btn_main_querydata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    protected void initView() {
        mTv_main_weather_desc = (TextView) findViewById(R.id.tv_main_weather_desc);
        btn_main_querydata = (Button) findViewById(R.id.btn_main_querydata);
        btn_main_querydata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.updateData("北京");
            }
        });
    }

    @Override
    protected void initcustomerActivityComponent(APPComponent _appcomponent) {
        DaggerMainActivityComponent.builder().aPPComponent(_appcomponent).mainActivityModule(new MainActivityModule(this)).build().inject(this);
    }

    @Override
    protected BasePresenter getCurrentPersenter() {
        return mMainPresenter;
    }

    @Override
    public void refresh() {

    }



    @Override
    public void loadMore() {

    }

    @Override
    public void updateData(WeatherInfo _weatherInfo) {
        if (_weatherInfo != null) {
            mTv_main_weather_desc.setText(_weatherInfo.getResult().getData().getRealtime().getCity_name()+":"+_weatherInfo.getResult().getData().getRealtime().getDataUptime());
        }
    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void showEmptyMsg(String msg) {

    }
}
