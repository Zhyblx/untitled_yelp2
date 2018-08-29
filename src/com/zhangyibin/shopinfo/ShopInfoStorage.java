package com.zhangyibin.shopinfo;

import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 类:ShopInfoStorage
 * 作用：存储企业信息；存储信息包括"店铺名称	评论数量	评论分数	经营范围	店铺地址	邮政编码	电话	价格范围	最近评论时间	第一次评论时间"。
 *
 */

public final class ShopInfoStorage {

    private static File file=null;
    private static OutputStream outputStream=null;
    private static OutputStreamWriter outputStreamWriter=null;
    private static BufferedWriter bufferedWriter=null;

    private static List<String> list=new ArrayList<String>();
    private static Iterator<String> iterator=null;

    private static String strName="";

    public static void getShopInfoStorage(List<String> Info){
        try{
            strName="";
            list.clear();
            list.addAll(Info);
            iterator=list.iterator();
            while (iterator.hasNext()){
                String str=iterator.next();
//                System.out.println(str);
                strName=strName+str+"|";

            }
            System.out.println(strName);

            file=new File("ShopInfo.txt");
            outputStream=new FileOutputStream(file,true);
            outputStreamWriter=new OutputStreamWriter(outputStream);
            bufferedWriter=new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(strName+"\r\n");
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();


        }catch (Exception e){

        }
    }


}
