package com.zhangyibin.yelp_interface;

/**
 * 接口：parameter
 * 作用：定义参数
 */

public interface Parameter {

    public static final String PARAMETER[] =
            new String[]
                    {"https://www.yelp.com/search?find_loc=Miami,+FL&start=" , //弗罗里达州餐厅公布信息
                            "[class=biz-name js-analytics-click]" , // 弗罗里达州餐厅公布信息详情页面链接获取参数
                            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36" , // 浏览器信息
                            "https://www.yelp.com" , // 域名+详情页面参数=餐厅详情页面链接
                            "parameter_URL.txt" , // 餐厅详情页面链接存储地址
                            "parameter_INFO.txt"// 餐厅信息存储地址

                    };
}
