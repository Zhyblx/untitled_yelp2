package com.zhangyibin.startup;

import com.zhangyibin.shopcomment.ShopCommentInfo;
import com.zhangyibin.shopcomment.ShopCommentInfoStorage;

/**
 * 类：StartShopCommentInfo
 * 作用：启动店铺评分的爬虫程序
 */

import java.io.*;

public class StartShopCommentInfo {

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
//                ShopHealthStorage.getShopHealthStorage(ShopHealthInfo.getShopHealthInfo(URL));
                ShopCommentInfoStorage.getShopCommentInfoStorage(ShopCommentInfo.getShopHealthInfo(URL));

            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

            System.out.println("评分数据获取结束！");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
