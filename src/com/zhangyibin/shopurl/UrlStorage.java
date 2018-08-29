package com.zhangyibin.shopurl;

import com.zhangyibin.yelp_interface.Parameter;

import java.io.File;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.BufferedWriter;

/**
 * 类：UrlStorage
 * 作用：存储餐厅详情的URL
 * 存储地址：parameter_URL.txt
 */

public final class UrlStorage {

    private static File file = null;
    private static OutputStream outputStream = null;
    private static OutputStreamWriter outputStreamWriter = null;
    private static BufferedWriter bufferedWriter = null;

    private static String mark="";

    public static String getUrlStorage(String str) {
        try {

            file = new File(Parameter.PARAMETER[4]);
            outputStream = new FileOutputStream(file , true);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(str + "\r\n");
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();

            //标记(mark)：当餐厅详情页面的URL抓取完毕，mark=1；
            mark="1";
        } catch (Exception e) {
//            System.getLogger("存储出错");
        }
        return mark;
    }

}
