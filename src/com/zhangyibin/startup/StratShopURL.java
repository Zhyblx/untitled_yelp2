package com.zhangyibin.startup;

import com.zhangyibin.shopurl.JsoupUrl;
import com.zhangyibin.shopurl.UrlStorage;

/**
 * 类：StratShopURL
 * 作用：获取并存储店铺详情页URL
 */

public class StratShopURL {

    public static void main(String[] args) {

        try {

            for (int i = 0; i < 1000; i = i + 10) {
                String str = JsoupUrl.getUrl("https://www.yelp.com/search?find_loc=Miami,+FL&start=" + i);
                UrlStorage.getUrlStorage(str);//存储
            }

            System.out.println("店铺详情页URL获取结束！");

        } catch (Exception e) {

        }

    }


}
