package com.zhangyibin.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static List<Object> getIpFromText(String url) {
        List<Object> ipList = new ArrayList<Object>();
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .timeout(3000)
                    .get();

            String ipStr = doc.body().text().trim().toString();

            String[] ips1=ipStr.split("HTTP");

            for (String ip : ips1) {
                ip=ip.trim().replaceAll("[A-Za-z\u4e00-\u9fa5]", "").trim();
                //System.out.println(ip);
                String[] sip=ip.split("\\s");
//                MyIp myIp=new MyIp(sip[0],sip[1]);
                ipList.add(sip);
            }

        } catch (IOException e) {
            System.out.println("加载文档出错");
        }
        return ipList;
    }

    public static void main(String[] args){
        test.getIpFromText("https://www.yelp.com/biz/versailles-restaurant-miami");
    }

}
