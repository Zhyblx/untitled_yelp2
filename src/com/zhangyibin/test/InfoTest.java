package com.zhangyibin.test;

import com.zhangyibin.shopinfo.JsoupShopInfo;
import com.zhangyibin.shopinfo.ShopInfoStorage;

/**
 * 测试类：InfoTest
 * 作用：用于获取和存储店铺信息
 *
 */

public class InfoTest {

    public static void main(String[] args){
//        System.out.println(JsoupShopInfo.getShopInfo("https://www.yelp.com/biz/jamon-iberico-pata-negra-restaurant-tapas-and-wine-bar-miami"));

        ShopInfoStorage.getShopInfoStorage(JsoupShopInfo.getShopInfo("https://www.yelp.com/biz/sonnys-bbq-florida-city"));

//        JsoupShopInfo.getShopInfo("https://www.yelp.com/biz/sonnys-bbq-florida-city?start=160");


    }

}
