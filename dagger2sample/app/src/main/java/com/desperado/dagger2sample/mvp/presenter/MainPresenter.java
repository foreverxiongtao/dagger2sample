package com.desperado.dagger2sample.mvp.presenter;

import com.desperado.dagger2sample.config.NetParams;
import com.desperado.dagger2sample.entity.WeatherInfo;
import com.desperado.dagger2sample.global.RemoteAPI;
import com.desperado.dagger2sample.mvp.model.MainModel;
import com.desperado.dagger2sample.mvp.view.MainView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

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
    public MainModel setUpModel() {
        return new MainModel(getRemoteAPI());
    }

    public void updateData(String cityname) {
        Subscriber<WeatherInfo> subscribe = new Subscriber<WeatherInfo>() {
            @Override
            public void onCompleted() {
                getView().dismissLoading();
            }

            @Override
            public void onError(Throwable e) {
                getView().showErrorMsg("网络异常");
            }

            @Override
            public void onNext(WeatherInfo _weatherInfo) {
                getView().updateData(_weatherInfo);
            }
        };
        Observable<WeatherInfo> localWeatherInfoObservable = getModel().getstudent(NetParams.getInstance().queryWeather(cityname));
        localWeatherInfoObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                getView().showLoadingDialog("加载中");
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(subscribe);
        addSubscrib(subscribe);
    }
}
