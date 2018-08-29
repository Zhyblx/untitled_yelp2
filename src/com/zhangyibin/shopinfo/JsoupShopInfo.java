package com.zhangyibin.shopinfo;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import com.zhangyibin.yelp_interface.Parameter;
import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 类：JsoupShopInfo
 * 作用：获取店铺信息
 * 获取字段：	店铺名称	评论数量	评论分数	经营范围	店铺地址	邮政编码	电话	价格范围	最近评论时间	第一次评论时间
 */

public final class JsoupShopInfo {

    private static List<String> list = new ArrayList<String>();

    private static Connection connection = null;
    private static Document document = null;
    private static Elements ShopName1 = null;
    private static Elements ShopName2 = null;

    private static Elements ReviewData = null;
    private static Elements ReviewQuantity = null;
    private static Elements ReviewFraction1 = null;
    private static Elements ReviewFraction2 = null;

    private static Elements BusinessScope = null;

    private static Elements Address = null;
    private static Elements Bizphone = null;
    private static Elements Pricerange = null;

    private static Elements ReviewList = null;
    private static Elements LatestComentDate = null;
    private static Elements Page = null;
    private static Elements FirstComentDate = null;

    public static List<String> getShopInfo(String ShopUrl) {
        try {
            try {
                list.clear();
                Thread.sleep(100000); //延迟1秒后执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            connection = Jsoup.connect(ShopUrl);
            connection.userAgent(Parameter.PARAMETER[2]);
            connection.ignoreContentType(true);
            connection.timeout(10000);

            try {
                document = connection.get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //获得店铺标题
            //说明：目前已经发现标题数据的规则就有两种
            ShopName1 = document.select("[class=biz-page-title embossed-text-white shortenough]");
//        System.out.print(ShopName1.text());

            ShopName2 = document.select("[class=biz-page-title embossed-text-white]");
//        System.out.print(ShopName2.text());

            String ShopName = ShopName1.text() + ShopName2.text();
//        System.out.println(ShopName);
            list.add(ShopName);//add店铺名称

            //点评数据
            ReviewData = document.select("[class=biz-rating biz-rating-very-large clearfix]");
            ReviewQuantity = ReviewData.select("[class=review-count rating-qualifier]");
            //获得评论数量
//        System.out.println(ReviewQuantity.text());
            list.add(ReviewQuantity.text());//add评论数量

            //点评分数
            //说明：目前已经发现点评分数的规则就有两种
            String Fraction1 = "";
            String Fraction2 = "";
            try {
                ReviewFraction1 = ReviewData.select("[class=i-stars i-stars--large-4-half rating-very-large]");
                String str1 = ReviewFraction1.html();
                Fraction1 = str1.substring(str1.indexOf("alt") + 5 , str1.length() - 2);
                //获取评论分数
                //System.out.println(Fraction1);


            } catch (Exception e) {
                //异常不做处理
            }

            try {
                ReviewFraction2 = ReviewData.select("[class=i-stars i-stars--large-4 rating-very-large]");
                String str2 = ReviewFraction2.html();
                Fraction2 = str2.substring(str2.indexOf("alt") + 5 , str2.length() - 2);
                //获取评论分数
                //System.out.println(Fraction2);
            } catch (Exception e) {
                //异常不做处理
            }
            String Fraction = Fraction1 + Fraction2;
//        System.out.println(Fraction);
            list.add(Fraction);//add评论分数


            //经营范围
            BusinessScope = document.select("[class=category-str-list]");
//        System.out.println(BusinessScope.get(0).text());
            list.add(BusinessScope.get(0).text());//add经营范围

//            for (int i = 0; i < BusinessScope.size(); i++) {
//                System.out.println(BusinessScope.get(i).text());
//            }


            //店铺地址
            Address = document.select("[class=map-box-address u-space-l4]");
//            System.out.println(Address.text());
            list.add(Address.text());//add店铺地址


            //邮政编码
            String zipcode = Address.text().substring(Address.text().indexOf(",") + 1 , Address.text().length());
//            System.out.println(zipcode);
            list.add(zipcode);//add邮政编码

            //电话
            Bizphone = document.select("[class=biz-phone]");
//            System.out.println(Bizphone.text());
            list.add(Bizphone.text());//add电话

            //价格范围
            Pricerange = document.select("[class=nowrap price-description]");
//            System.out.println(Pricerange.text());
            list.add(Pricerange.text());//add价格范围


            //评论列表
            ReviewList = document.select("[class=ylist ylist-bordered reviews]");
            //最新评论时间
            LatestComentDate = ReviewList.select("[class=biz-rating biz-rating-large clearfix]");
//        System.out.println(RecentlyReviewTimer.get(0).text());
            list.add(LatestComentDate.get(0).text());//add最近评论时间

            //第一次评论时间
            Page = document.select("[class=page-of-pages arrange_unit arrange_unit--fill]");
            String strPage = Page.text();
            int Pageend = Integer.valueOf(strPage.substring(strPage.indexOf("of") + 3 , strPage.length()));
//            System.out.println(Pageend);
            String PageEnd_URL = ShopUrl + "?start=" + ((Pageend - 1) * 20);
//            System.out.println(PageEnd_URL);
            try {
//                Thread.sleep(1000); //延迟1秒后执行
                String EndTimer = "";
                Connection connection1 = Jsoup.connect(PageEnd_URL);
                connection1.userAgent(Parameter.PARAMETER[2]);
                connection1.ignoreContentType(true);
                connection1.timeout(10000);
                Document document1 = connection1.get();
                Elements elements1 = document1.select("[class=rating-qualifier]");
//                System.out.println(elements1.get(elements1.size()-1).text());
                EndTimer = elements1.get(elements1.size() - 1).text();
                list.add(EndTimer);
            } catch (Exception e) {
//                e.printStackTrace();
            }

            list.add(ShopUrl);

        } catch (Exception e) {
            // 不做异常处理
        }

        return list;
    }
}
