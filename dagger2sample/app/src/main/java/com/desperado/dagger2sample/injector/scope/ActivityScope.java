package com.desperado.dagger2sample.injector.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/*
 *
 *
 * 版 权 :@Copyright 北京****科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/14  10:50
 *
 * 描 述 :自定义的范围注解，注解对象的生命周期应该遵循activity的生命周期
 *
 * 修订日期 :
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
