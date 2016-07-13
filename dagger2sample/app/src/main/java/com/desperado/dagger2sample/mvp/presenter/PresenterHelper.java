package com.desperado.dagger2sample.mvp.presenter;

import com.desperado.dagger2sample.mvp.view.BaseView;

/**
 * Created by Administrator on 2016/7/13.
 */
public interface PresenterHelper<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
