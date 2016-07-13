package com.desperado.dagger2sample.mvp.view;

import android.view.View;

/*
 *
 *
 * 版 权 :@Copyright 北京优多鲜道科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/13  19:46
 *
 * 描 述 :界面处理基类 mvp view层
 *
 * 修订日期 :
 */
public interface BaseView {
    //显示加载
    void showLoadingDialog(String msg);

    //隐藏加载
    void dismissLoading();

    //显示错误信息
    void showErrorMsg(String msg, View.OnClickListener onClickListener);

    //显示空信息
    void showEmptyMsg(String msg, View.OnClickListener onClickListener);

    //网络异常
    void showNetError(View.OnClickListener onClickListener);
}
