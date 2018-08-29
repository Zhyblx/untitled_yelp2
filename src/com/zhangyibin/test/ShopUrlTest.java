package com.zhangyibin.test;

import com.zhangyibin.shopurl.JsoupUrl;
import com.zhangyibin.shopurl.UrlStorage;
import com.zhangyibin.yelp_interface.Parameter;

/**
 * 测试类：ShopUrlTest
 * 作用：用于测试获取并存储店铺详情的URL
 */

public class ShopUrlTest {

    public static void main(String[] args) throws Exception {
//        System.out.println(JsoupUrl.getUrl(Parameter.PARAMETER[0]+10));
        String str=JsoupUrl.getUrl(Parameter.PARAMETER[0]+10);
        UrlStorage.getUrlStorage(str);//存储
    }
}
