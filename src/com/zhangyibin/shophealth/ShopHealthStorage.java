package com.zhangyibin.shophealth;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

/**
 * 类：ShopHealthStorage
 * 作用：存储店铺健康信息，存储字段包括"Name，Date，InspectionType，Violations，Result"；
 */

public final class ShopHealthStorage {

    private static File file = null;
    private static OutputStream outputStream = null;
    private static OutputStreamWriter outputStreamWriter = null;
    private static BufferedWriter bufferedWriter = null;

    private static List<String> list = new ArrayList<String>();
    private static Iterator<String> iterator = null;

    public static void getShopHealthStorage(List<String> HealthInfo) {
        try {

            String strName = "";
            list.clear();
            list.addAll(HealthInfo);
            iterator = list.iterator();
            while (iterator.hasNext()) {
                String str = iterator.next();
                strName = strName + str + "\r\n";
            }

            file = new File("ShopHealth.txt");
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
