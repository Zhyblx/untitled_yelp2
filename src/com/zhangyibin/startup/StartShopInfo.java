package com.zhangyibin.startup;

import com.zhangyibin.shopinfo.JsoupShopInfo;
import com.zhangyibin.shopinfo.ShopInfoStorage;

import java.io.*;

/**
 * 类：StartShopInfo
 * 作用：启动店铺信息的爬虫程序
 */

public class StartShopInfo {

    private static File file = null;
    private static InputStream inputStream = null;
    private static InputStreamReader inputStreamReader = null;
    private static BufferedReader bufferedReader = null;

    public static void main(String[] args) {
        try{

            file = new File("parameter_URL.txt");
            inputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String URL = "";
            while ((URL = bufferedReader.readLine()) != null) {
                ShopInfoStorage.getShopInfoStorage(JsoupShopInfo.getShopInfo(URL));

            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

            System.out.println("店铺信息获取结束！");

        }catch(Exception e){
            //
        }
    }

}
