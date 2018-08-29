package com.zhangyibin.test;

import com.zhangyibin.shopcomment.ShopCommentInfo;
import com.zhangyibin.shopcomment.ShopCommentInfoStorage;

public class ShopCommentInfoTest {

    public static void main(String[] args){
        try{
//            System.out.println(ShopCommentInfo.getShopHealthInfo("https://www.yelp.com/biz/sweet-dogs-miami"));

//            ShopCommentInfo.getShopHealthInfo("https://www.yelp.com/biz/sweet-dogs-miami");
            ShopCommentInfoStorage.getShopCommentInfoStorage(ShopCommentInfo.getShopHealthInfo("https://www.yelp.com/biz/sweet-dogs-miami"));

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
