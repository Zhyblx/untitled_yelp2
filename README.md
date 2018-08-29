# 海外网站信息抓取项目

----
## 一、项目背景

爬取yelp.com网站所有弗罗里达州（Florida）餐厅公布信息；<br>
抓取的信息包含三部分内容：<br>
1)店铺的基本信息；字段包括【店铺名称、评论数量、评论分数、经营范围、店铺地址、邮政编码、电话、价格范围、最近评论时间、第一次评论时间、店铺URL】。<br>
2)店铺的健康数据；字段包括【id、Date、InspectionType、Violations、Result】。<br>
3)店铺的评论数据；字段包括【店铺名称、评论时间、评分】。<br>

## 二、项目说明
1)项目实现网站数据的抓取是基于开源框架JSOUP实现。<br>
2)整个项目代码都书写在com.zhangyibin目录下；主要分为四个部分shopurl(店铺详情URL)、shopinfo(店铺信息)、shophealth(健康数据)和shopcomment(评论数据)。<br>

## 三、实现过程
1)优先抓取店铺详情页URL；执行类：StartShopURL.java；获取到的店铺详情页URL会自动存储在parameter_URL.txt文件中。<br>
2)基于店铺详情URL的获取，执行类：StartShopInfo.java；遍历parameter_URL.txt文件中的URL，完成店铺信息的抓取并存储；店铺信息会自动存储在ShopInfo.txt文件中。<br>
3)基于店铺详情URL的获取，执行类：StartShopHealthInfo.java；遍历parameter_URL.txt文件中的URL，完成店铺健康数据的抓取并存储，健康信息会自动存储在ShopHealth.txt文件中。<br>
4)基于店铺详情URL的获取，执行类：StartShopCommentInfo.java；遍历parameter_URL.txt文件中的URL，完成店铺评论/评分数据的抓取并存储，评论/评分信息会自动存储在CommentInfo.txt文件中。<br>

## 四、重点部分说明
**一、店铺详情页URL的获取**
1)佛罗里达州企业店铺列表页面地址：https://www.yelp.com/search?find_loc=Miami,+FL&start=0   (注意：start参数的步长是10)<br>
2)执行类JsoupUrl.java抓取店铺详情URL。示例：<br>

    String str=elements.get(i).select("a").first().attr("href");<br>
3)执行类UrlStorage.java对抓取的店铺URL进行存储。存放地址根目录下parameter_URL.txt文件中。<br>

**二、店铺信息的获取**
1)对于店铺信息的抓取，困难点主要在于历史评论时间(即，第一次评论时间)的获取。在完成连接店铺URL获取店铺名称等数据的同时还需重新连接一次评论URL从而进行获取到第一次评论时间。示例：<br>

            //第一次评论时间
            Page = document.select("[class=page-of-pages arrange_unit arrange_unit--fill]");
            String strPage = Page.text();
            int Pageend = Integer.valueOf(strPage.substring(strPage.indexOf("of") + 3 , strPage.length()));

            String PageEnd_URL = ShopUrl + "?start=" + ((Pageend - 1) * 20);

            try {
                Thread.sleep(1000); //延迟1秒后执行
                String EndTimer = "";
                Connection connection1 = Jsoup.connect(PageEnd_URL);
                connection1.userAgent(Parameter.PARAMETER[2]);
                connection1.ignoreContentType(true);
                connection1.ignoreHttpErrors(true);
                connection1.maxBodySize(0);
                connection1.timeout(5000);
                Document document1 = connection1.get();
                Elements elements1 = document1.select("[class=rating-qualifier]");

                EndTimer = elements1.get(elements1.size() - 1).text();
                list.add(EndTimer);
            } catch (Exception e) {

            }
2)执行类JsoupShopInfo.java抓取店铺信息。<br>
3)执行类ShopInfoStorage.java对抓取的店铺信息进行存储。存放地址根目录下ShopInfo.txt文件中。<br>

**三、店铺健康数据的获取**
1)店铺健康数据的所在页面为店铺详情页面的子页面，所以要获取健康数据需要先抓取健康数据所在的子页面。示例：<br>

        elements_Details = document_Details.select("[class=attribute-key]");
        //获得健康详情页面的URL
        String strUrl = "https://www.yelp.com" + elements_Details.select("a").first().attr("href");
        System.out.println(strUrl);
2)执行类ShopHealthInfo.java抓取店铺健康数据。<br>
3)执行类ShopHealthStorage.java对抓取的店铺健康数据进行存储。存放地址根目录下ShopHealth.txt文件中。<br>

**四、店铺评论数据的获取**
1)店铺评论数据的所在页面为店铺详情页面的子页面，所以要获取评论数据需要先抓取评论数据所在的子页面，同时评论数据所在的子页面较多，会涉及评论页面的遍历。示例：<br>
        try {
            for (int i = 0; i < Page_num; i = i + 20) {
                //System.out.println(i);

                String PageEnd_URL = ShopUrl + "?start=" + i;
                System.out.println(PageEnd_URL);

                Thread.sleep(1000); //延迟1秒后执行
                String ShopCommentData = "";
                Connection connection1 = Jsoup.connect(PageEnd_URL);
                connection1.userAgent(Parameter.PARAMETER[2]);
                connection1.ignoreContentType(true);
                connection1.timeout(5000);
                Document document1 = connection1.get();
                Elements elements1 = document1.select("[class=ylist ylist-bordered reviews]");

                Elements elements2 = elements1.select("[class=review review--with-sidebar]");
                Elements elements3 = elements2.select("[class=biz-rating biz-rating-large clearfix]");
                for (int j = 0; j < elements3.size(); j++) {
                    ShopCommentInfo="";
                    String str = elements3.get(j).html();
                    System.out.println(elements3.get(j).text());// 评论时间
                    int a = str.indexOf("alt");
                    System.out.println(str.substring(a+5,a+9));
                    ShopCommentInfo=ShopName+","+elements3.get(j).text()+","+str.substring(a+5,a+9);
                    list.add(ShopCommentInfo);

                }
            }
        } catch (Exception e) {
            // 不处理
        }

2)执行类ShopCommentInfo.java抓取店铺评论数据。<br>
3)执行类ShopCommentInfoStorage.java对抓取的店铺评论数据进行存储。存放地址根目录下CommentInfo.txt文件中。<br>

## 五、数据格式：

**店铺信息数据格式**

<table>
<tr>
<td>店铺名称</td>
<td>评论数量</td>
<td>评论分数</td>
<td>经营范围</td>
<td>店铺地址</td>
<td>邮政编码</td>
<td>电话</td>
<td>价格范围</td>
<td>最近评论时间</td>
<td>第一次评论时间</td>
<td>店铺链接</td>
</tr>

<tr>
<td>Versailles Restaurant</td>
<td>3622 reviews</td>
<td>4.0 star rating</td>
<td>Cuban, Coffee & Tea, Latin American</td>
<td>3555 SW 8th St Miami, FL 33135 West Flagler</td>
<td> FL 33135 West Flagler</td>
<td>(305) 444-0240</td>
<td>$11-30</td>
<td>8/21/2018</td>
<td>3/6/2009</td>
<td>https://www.yelp.com/biz/versailles-restaurant-miami</td>
</tr>
</table>



**店铺健康数据数据格式**
<table>
<tr>
<td>Name</td>
<td>Date</td>
<td>Inspection Type</td>
<td>Violations</td>
<td>Result</td>
</tr>

<tr>
<td>La Ventana Restaurant</td>
<td>August 24, 2015</td>
<td>Routine Type</td>
<td>18  N/A</td>
<td>Pass</td>
</tr>

</table>


**店铺评论数据数据格式**

<table>
<tr>
<td>店铺名称</td>
<td>评论时间</td>
<td>评分</td>
</tr>

<tr>
<td>La Ventana Restaurant</td>
<td>4/17/2015</td>
<td>5.0</td>
</tr>
</table>

