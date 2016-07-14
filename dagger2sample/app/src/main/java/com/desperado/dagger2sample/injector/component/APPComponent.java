package com.desperado.dagger2sample.injector.component;

import com.desperado.dagger2sample.GlobalApplication;
import com.desperado.dagger2sample.global.RemoteAPI;
import com.desperado.dagger2sample.injector.module.APIModule;
import com.desperado.dagger2sample.injector.module.APPModule;

import javax.inject.Singleton;

import dagger.Component;

/*
 *
 *
 * 版 权 :@Copyright 北京xxxxx科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/13  18:08
 *
 * 描 述 :全局连接器，连接对应需要注入依赖的对象，已经暴露一些方法给需要依赖的其他注入器或者子注入器
 *
 * 修订日期 :
 */
@Singleton
@Component(modules ={APIModule.class, APPModule.class})
public interface APPComponent {
    final class Initializer {
        private Initializer() {
            //空实现
        }

        /***
         * 获取连接器实例
         *
         * @param _application
         * @return
         */
        public static APPComponent init(GlobalApplication _application) {
            return null;
        }
    }

    RemoteAPI getRemoteAPI();
}
