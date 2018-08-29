package com.zhangyibin.shopurl;

import com.zhangyibin.yelp_interface.Parameter;
import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 类：JsoupUrl
 * 作用： 获取弗罗里达州餐厅信息
 */

public final class JsoupUrl  {

    private static Document document=null;
    private static Connection connection=null;
    private static Elements elements=null;
    private static String hrefUrl="";

    public static String getUrl(String URL){

        try{

            Thread.sleep(1000);
            // 清空元素
            hrefUrl="";

            connection=Jsoup.connect(URL);
            connection.userAgent(Parameter.PARAMETER[2]);
            connection.ignoreContentType(true);
            connection.timeout(5000);

            document=connection.get();

            elements=document.select(Parameter.PARAMETER[1]);
            for(int i=0;i<elements.size();i++){
                String str=elements.get(i).select("a").first().attr("href");
                hrefUrl=hrefUrl+Parameter.PARAMETER[3]+str+"\r\n";
                System.out.println(hrefUrl);
            }

        }catch(Exception e){
//            System.getLogger("爬虫错误");
        }

        return hrefUrl;
    }

}
