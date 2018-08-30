package com.zhangyibin.startup;

import com.zhangyibin.shophealth.ShopHealthInfo;
import com.zhangyibin.shophealth.ShopHealthStorage;

/**
 * 类：StartShopHealthInfo
 * 作用：启动健康数据的爬虫程序
 */

import java.io.*;

public class StartShopHealthInfo {

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
                System.out.println(URL);
                ShopHealthStorage.getShopHealthStorage(ShopHealthInfo.getShopHealthInfo(URL));

            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

            System.out.println("健康数据获取结束！");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
