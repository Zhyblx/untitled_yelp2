package com.zhangyibin.shopcomment;

import java.util.List;
import java.util.ArrayList;

import com.zhangyibin.yelp_interface.Parameter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;

public class ShopCommentInfo {

    private static List<String> list=new ArrayList<String>();

    private static Connection connection=null;
    private static Document document=null;

    private static Elements ShopName1 = null;
    private static Elements ShopName2 = null;

    private static String ip="1.197.59.83" ;
    private static int port=33067;

    public static List<String> getShopHealthInfo(String ShopUrl) {

        try{

            // 设置代理

            /**
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
            URL url = new URL(ShopUrl);
            HttpsURLConnection urlcon = (HttpsURLConnection)url.openConnection(proxy);
            urlcon.connect();         //获取连接
            InputStream is = urlcon.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
            StringBuffer bs = new StringBuffer();
            String l = null;
            while((l=buffer.readLine())!=null){
                bs.append(l);
            }

            System.out.println(bs.toString());
             */
            list.clear();
//            Thread.sleep(100000);

            connection = Jsoup.connect(ShopUrl);
            connection.proxy(ip,port);
            connection.userAgent("Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0");
            connection.ignoreContentType(true);
            connection.timeout(10000);
            document=connection.get();
//            document=Jsoup.parse(bs.toString());

//            System.out.println(document.html());

            //获得店铺标题
            //说明：目前已经发现标题数据的规则就有两种
            ShopName1 = document.select("[class=biz-page-title embossed-text-white shortenough]");
//        System.out.print(ShopName1.text());

            ShopName2 = document.select("[class=biz-page-title embossed-text-white]");
//        System.out.print(ShopName2.text());

            String ShopName = ShopName1.text() + ShopName2.text();
//        System.out.println(ShopName);
            list.add(ShopName);//add店铺名称

            String str=document.html();
//            int stra=str.indexOf("id=\"rating-details-modal-content\"");
            int stra=str.indexOf("data-monthly-ratings=\"");
            int strb=str.indexOf("]]}\"> ");
//            System.out.println(str.substring(stra+22,strb+2));
            list.add(str.substring(stra+22,strb+2));

        }catch(Exception e){
            //
        }

        return list;
    }


}
