package com.desperado.dagger2sample.mvp.presenter;

import com.desperado.dagger2sample.global.RemoteAPI;
import com.desperado.dagger2sample.mvp.model.BaseModel;
import com.desperado.dagger2sample.mvp.view.BaseView;
import com.desperado.dagger2sample.utils.AbLogUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

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
        mModel = setUpModel();
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


    /***
     * 集合用于添加订阅对象
     */
    private List<Subscriber> mSubscribers = new ArrayList<>();

    /**
     * 取消任务订阅
     **/
    public void unsubcrib() {
        try {
            if (mSubscribers != null && !mSubscribers.isEmpty()) {
                for (int i = 0; i < mSubscribers.size(); i++) {
                    Subscriber mSubscriber = mSubscribers.get(i);
                    if (mSubscriber != null && !mSubscriber.isUnsubscribed()) {
                        mSubscriber.unsubscribe();
                        AbLogUtil.i("basePresenter", mSubscriber.getClass().getSimpleName() + "取消订阅成功");
                    }
                }
            }
        } catch (Exception e) {

            AbLogUtil.i("basePresenter", "取消订阅失败....");
        }
    }

    /**
     * 添加当前的订阅对象到集合中去
     **/
    public void addSubscrib(Subscriber _subscriber) {
        mSubscribers.add(_subscriber);
    }

}
