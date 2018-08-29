package com.zhangyibin.shophealth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.zhangyibin.yelp_interface.Parameter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 类：ShopHealthInfo
 * 作用：获取店铺的健康信息；具体的健康信息的表格如下：
 * La Ventana Restaurant August 24, 2015 Routine 18  N/A Pass
 * La Ventana Restaurant July 23, 2015 Routine 28  N/A Pass
 * La Ventana Restaurant January 13, 2015 Routine 15  N/A Pass
 * La Ventana Restaurant July 29, 2014 Routine 17  N/A Pass
 * La Ventana Restaurant December 26, 2013 Routine 14  N/A Pass
 * La Ventana Restaurant August 13, 2013 Routine 19  N/A Pass
 * La Ventana Restaurant July 16, 2013 Routine 0 N/A Pass
 * La Ventana Restaurant March 28, 2013 Routine 9  N/A Pass
 * La Ventana Restaurant November 29, 2012 Routine 0 N/A Pass
 * La Ventana Restaurant September 17, 2012 Routine 2  N/A Pass
 * La Ventana Restaurant March 1, 2012 Routine 2  N/A Pass
 * La Ventana Restaurant August 24, 2011 Routine 3  N/A Pass
 * La Ventana Restaurant February 25, 2011 Routine 5  N/A Pass
 * La Ventana Restaurant August 24, 2010 Routine 5  N/A Pass
 */

public final class ShopHealthInfo {

    private static Connection connection_Details = null;
    private static Document document_Details = null;
    private static Elements elements_Details = null;

    private static Connection connection_HealthInfo = null;
    private static Document document_HealthInfo = null;

    private static Elements elements_HealthInfo_Name = null;
    private static Elements elements_HealthInfo_List = null;
    private static Elements elements_HealthInfo_List_tbody = null;
    private static Elements elements_HealthInfo_List_tbody_tr = null;

    public static List<String> getShopHealthInfo(String ShopUrl) {

        List<String> list = new ArrayList<String>();
        String strUrl="";

        try {
            list.clear();
            Thread.sleep(100000); //延迟1秒后执行
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection_Details = Jsoup.connect(ShopUrl);
        connection_Details.userAgent(Parameter.PARAMETER[2]);
        connection_Details.ignoreContentType(true);
        connection_Details.timeout(10000);
        try {
            document_Details = connection_Details.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        elements_Details = document_Details.select("[class=attribute-key]");
        try{
            //获得健康详情页面的URL
            strUrl = "https://www.yelp.com" + elements_Details.select("a").first().attr("href");
            System.out.println(strUrl);
        }catch (Exception e){
            strUrl="";
        }

        // strUrl不等于空，则执行健康信息的抓取
        if("".equals(strUrl)){

        }else {
            connection_HealthInfo = Jsoup.connect(strUrl);
            connection_HealthInfo.userAgent(Parameter.PARAMETER[2]);
            connection_HealthInfo.ignoreContentType(true);
            connection_HealthInfo.timeout(10000);

            try {
                document_HealthInfo = connection_HealthInfo.get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            elements_HealthInfo_Name = document_HealthInfo.select("[class=biz-name js-analytics-click]");
//        System.out.println(elements_HealthInfo_Name.text());//店铺名称

            //class=table table-striped js-inspections-table hide-city-grade
            //class=table table-striped js-inspections-table hide-city-grade
            elements_HealthInfo_List = document_HealthInfo.select("[class=table table-striped js-inspections-table hide-city-grade]");
            elements_HealthInfo_List_tbody = elements_HealthInfo_List.select("tbody");
            elements_HealthInfo_List_tbody_tr = elements_HealthInfo_List_tbody.select("tr");

            for (int i = 0; i < elements_HealthInfo_List_tbody_tr.size(); i++) {
//            elements_HealthInfo_List_tbody_td=elements_HealthInfo_List_tbody_tr.get(i).select("td");
//            System.out.println(elements_HealthInfo_List_tbody_tr.get(i).text());
                String Tabledata = elements_HealthInfo_List_tbody_tr.get(i).text();
                if (Tabledata.indexOf("Violations:") != -1) {
                    int Tabledata_a = Tabledata.indexOf("Violations:");
                    int Tabledata_b = Tabledata.indexOf("N/A");
//                System.out.println(elements_HealthInfo_Name + " " + Tabledata.substring(0 , Tabledata_a) + " " + Tabledata.substring(Tabledata_b , Tabledata.length()));
                    list.add(elements_HealthInfo_Name.text() + " " + Tabledata.substring(0 , Tabledata_a) + " " + Tabledata.substring(Tabledata_b , Tabledata.length()));

                } else {
//                System.out.println(elements_HealthInfo_Name + " " + Tabledata);
                    list.add(elements_HealthInfo_Name.text() + " " + Tabledata);
                }

            }
        }

        return list;
    }
}
























