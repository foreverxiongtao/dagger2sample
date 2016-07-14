package com.desperado.dagger2sample.config;

import java.util.HashMap;
import java.util.Map;

/*
 *
 *
 * 版 权 :@Copyright 北京&*******科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/14  14:10
 *
 * 描 述 :接口请求参数
 *
 * 修订日期 :
 */
public class NetParams {
    private static NetParams params = new NetParams();

    private NetParams() {

    }

    public static NetParams getInstance() {
        return params;
    }


    /***
     * 查询天气
     * @param cityname
     * @return
     */
    public Map<String, String> queryWeather(String cityname) {
        HashMap<String, String> map = new HashMap<>();
        map.put("cityname", cityname);
        map.put("dtype", "json");
        map.put("key","e990a83a0497033cc7a9689a4ec1fb23");
        return map;
    }
}
