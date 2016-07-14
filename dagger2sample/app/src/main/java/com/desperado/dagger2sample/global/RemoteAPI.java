package com.desperado.dagger2sample.global;

import com.desperado.dagger2sample.entity.WeatherInfo;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/*
 *
 *
 * 版 权 :@Copyright 北京优多鲜道科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/13  18:42
 *
 * 描 述 :服务器请求地址
 *
 * 修订日期 :
 */
public interface RemoteAPI {

    /****
     * 获取所有学生信息的接口
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("weather/query")
    Observable<WeatherInfo> queryweather(@FieldMap Map<String, String> params);
}
