package com.desperado.dagger2sample.mvp.presenter;

/*
 *
 *
 * 版 权 :@Copyright 北京*****科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/14  10:17
 *
 * 描 述 :业务处理辅助类
 *
 * 修订日期 :
 */
public interface PresenterHelper<V, M> {

    void initModle(M _model);

    void attachView(V _view);

    void detachView();

}
