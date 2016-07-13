package com.desperado.dagger2sample.injector.module;

import com.desperado.dagger2sample.global.RemoteAPI;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 *
 *
 * 版 权 :@Copyright 北京xxxxx科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/13  18:39
 *
 * 描 述 :服务器接口模块
 *
 * 修订日期 :
 */
@Module
public class APIModule {
    /**
     * 服务器请求地址基类
     */
    public static final String BASE_URL = "http://www.xxx.com/";


    /***
     * 获取okhttpclient实例主要可以用于不同页面进行网络拦截设置，或者读取，写入超时d单独设置
     *
     * @return
     */
    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build();
    }

    /***
     * 创建retrofit实例，暴露remoteapi实例，供外部需要注入的对象进行调用
     *
     * @param _client
     * @return
     */
    @Singleton
    @Provides
    public RemoteAPI provideRemoteAPI(OkHttpClient _client) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).client(_client).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(RemoteAPI.class);
    }
}
