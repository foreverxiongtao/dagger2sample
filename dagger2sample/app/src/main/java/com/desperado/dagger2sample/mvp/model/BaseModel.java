package com.desperado.dagger2sample.mvp.model;

import com.desperado.dagger2sample.global.RemoteAPI;

/*
 *
 *
 * 版 权 :@Copyright 北京****科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/13  19:38
 *
 * 描 述 :数据获取基类
 *
 * 修订日期 :
 */
public class BaseModel {
    protected RemoteAPI mRemoteAPI;

    public BaseModel(RemoteAPI _remoteAPI) {
        this.mRemoteAPI = _remoteAPI;
    }
}
