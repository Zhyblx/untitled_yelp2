package com.zhangyibin.shopcomment;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 类：ShopCommentInfoStorage
 * 作用：用于存储店铺的评分信息，并将文件存储在根目录ShopComment.txt下。
 */

public class ShopCommentInfoStorage {

    private static File file = null;
    private static OutputStream outputStream = null;
    private static OutputStreamWriter outputStreamWriter = null;
    private static BufferedWriter bufferedWriter = null;

    private static List<String> list = new ArrayList<String>();
    private static Iterator<String> iterator = null;

    public static void getShopCommentInfoStorage(List<String> HealthInfo) {
        try {

            String strName = "";
            list.clear();
            list.addAll(HealthInfo);
            strName=list.toString();
            System.out.println(strName);

            file = new File("ShopComment.txt");
            outputStream = new FileOutputStream(file , true);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(strName + "\r\n");
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
