package com.zhangyibin.test;

import com.zhangyibin.shophealth.ShopHealthInfo;
import com.zhangyibin.shophealth.ShopHealthStorage;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 测试类：ShopHealthInfoTest
 * 作用：用于存储和获取店铺的健康信息
 *
 */

public class ShopHealthInfoTest {

    public static void main(String[] args) {
//        ShopHealthInfo.getShopHealthInfo("https://www.yelp.com/biz/la-ventana-restaurant-miami-beach");
//        List<String> list = new ArrayList<String>();
//        list.addAll(ShopHealthInfo.getShopHealthInfo("https://www.yelp.com/biz/la-ventana-restaurant-miami-beach"));
//        Iterator<String> iterator = list.iterator();
//        do {
//            System.out.println(iterator.next());
//        } while (iterator.hasNext());

        ShopHealthInfo.getShopHealthInfo("https://www.yelp.com/biz/la-ventana-restaurant-miami-beach");

//        ShopHealthStorage.getShopHealthStorage(ShopHealthInfo.getShopHealthInfo("https://www.yelp.com/biz/la-ventana-restaurant-miami-beach"));

    }

}
