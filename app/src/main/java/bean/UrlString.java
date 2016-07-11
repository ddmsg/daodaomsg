package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class UrlString{
    //头条的网址
    public static String toutiaoUrl = "http://c.3g.163.com//nc/article/headline/T1348647909107/%d-20.html?from=toutiao&size=20&prog=LTitleA&fn=8&passport=&devId=CbV5jf0I9pSHAda52GA%2BNA%3D%3D&lat=2aCqnnRFB0TRxekb9hcNvg%3D%3D&lon=XTrE8hcoRKgpXEiQD9uOIA%3D%3D&version=11.0&net=wifi&ts=1467622480&sign=EG%2FKjVsG18JbOY7bNCKbCax5T6Wbmn7j8oyGeT8Rji148ErR02zJ6%2FKXOnxX046I&encryption=1&canal=miliao_news&mac=5aa3B6sZ%2FpVLXjbbQ%2FVlmoe1A3IUPLLdoCiqBVf2Go0%3D";
    //娱乐的网址
    public static String yuleUrl = "http://c.3g.163.com//nc/article/list/T1348648517839/%d-20.html";
    //热点的网址
    public static String redianUrl = "http://c.3g.163.com//recommend/getSubDocPic?size=20&passport=&devId=CbV5jf0I9pSHAda52GA%2BNA%3D%3D&lat=2aCqnnRFB0TRxekb9hcNvg%3D%3D&lon=XTrE8hcoRKgpXEiQD9uOIA%3D%3D&version=11.0&net=wifi&ts=1467623455&sign=jo7IOWNEo0QBhAzJ9Z6J3WZsF9eTAobwZkuD8Q6UlA548ErR02zJ6%2FKXOnxX046I&encryption=1&canal=miliao_news&mac=5aa3B6sZ%2FpVLXjbbQ%2FVlmoe1A3IUPLLdoCiqBVf2Go0%3D";
    //体育的解析
    public static String tiyuUrl = "http://c.3g.163.com//nc/article/list/T1348649079062/%d-20.html";
    //财经
    public static String caijing = "http://c.3g.163.com//nc/article/list/T1348648756099/%d-20.html";
    //科技
    public static String kejiUrl = "http://c.3g.163.com//nc/article/list/T1348649580692/%d-20.html";
    //汽车
    public static String qicheUrl = "http://c.3g.163.com//nc/auto/list/5rex5Zyz/%d-20.html";
    //时尚
    public static String shishangUrl = "http://c.3g.163.com//nc/article/list/T1348650593803/%d-20.html";
    //图片
    public static String imageUrl = "http://c.3g.163.com//photo/api/list/0096/4GJ60096.json";
    //轻松一刻
    public static String qingsongUrl = "http://c.3g.163.com//nc/article/list/T1350383429665/%d-20.html";
    //段子
    public static String duanziUrl = "http://c.3g.163.com//recommend/getChanListNews?channel=T1419316284722&size=20&passport=&devId=CbV5jf0I9pSHAda52GA%2BNA%3D%3D&lat=6jRmECAynOwA3VbccEzuQw%3D%3D&lon=JgLl5zm0p5FO4lND7oQ9KA%3D%3D&version=11.0&net=wifi&ts=1467624825&sign=B9vK90Tc30LLYkPXDBDoUGDKK%2FJEVwA9mnwYaNNT%2B%2F948ErR02zJ6%2FKXOnxX046I&encryption=1&canal=miliao_news&mac=5aa3B6sZ%2FpVLXjbbQ%2FVlmoe1A3IUPLLdoCiqBVf2Go0%3D";
    //军事
    public static String junshiUrl = "http://c.3g.163.com//nc/article/list/T1348648141035/0-20.html";
    //历史
    public static String lishiUrl = "http://c.3g.163.com//nc/article/list/T1368497029546/0-20.html";
    //家居
    public static String jiajuUrl = "http://c.3g.163.com//nc/article/list/T1348654105308/0-20.html";
    //独家
    public static String dujiaUrl = "http://c.3g.163.com//nc/article/list/T1370583240249/0-20.html";
    //游戏
    public static String youxiUrl = "http://c.3g.163.com//nc/article/list/T1348654151579/0-20.html";
    //健康
    public static String jiankangUrl = "http://c.3g.163.com//nc/article/list/T1414389941036/0-20.html";
    //政务
    public static String zhengwuYrl = "http://c.3g.163.com//nc/article/list/T1414142214384/0-20.html";
    //漫画
    public static String manhuaUrl = "http://c.3g.163.com//nc/article/list/T1444270454635/0-20.html";
    //哒哒
    public static String dadaUrl = "http://c.3g.163.com//nc/article/list/T1444289532601/0-20.html";
    //彩票
    public static String caipiaoUrl = "http://c.3g.163.com//nc/article/list/T1356600029035/0-20.html";
    //美女
    public static String meinvUrl = "http://c.3g.163.com//recommend/getChanListNews?channel=T1456112189138&size=20&passport=&devId=CbV5jf0I9pSHAda52GA%2BNA%3D%3D&lat=6jRmECAynOwA3VbccEzuQw%3D%3D&lon=JgLl5zm0p5FO4lND7oQ9KA%3D%3D&version=11.0&net=wifi&ts=1467626467&sign=OGaLtpnmZG2srHnyeccI9m74qVfllrnEMtOATWrtWQl48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=miliao_news&mac=5aa3B6sZ%2FpVLXjbbQ%2FVlmoe1A3IUPLLdoCiqBVf2Go0%3D";

    //网址的集合
    public static List<String> getUrlList(){
        List<String> urlList = new ArrayList<>();
        urlList.add(toutiaoUrl);//0  头条
        urlList.add(yuleUrl);//1  娱乐
        urlList.add(redianUrl);//2   热点
        urlList.add(tiyuUrl);//3   体育
        urlList.add(caijing);//4  财经
        urlList.add(kejiUrl);//5  科技
        urlList.add(qicheUrl);//6   汽车
        urlList.add(shishangUrl);//7  时尚
       /* urlList.add(imageUrl);//8  图片

        urlList.add(qingsonUrl);//9  轻松一刻
        urlList.add(duanziUrl);//10   段子
        urlList.add(junshiUrl);//11   军事

        urlList.add(lishiUrl);//12   历史
        urlList.add(jiajuUrl);//13  家居
        urlList.add(dujiaUrl);//14  独家
        urlList.add(youxiUrl);//15  游戏
        urlList.add(jiankangUrl);//16   健康
        urlList.add(zhengwuYrl);//17  政务
        urlList.add(manhuaUrl);//18  漫画
        urlList.add(dadaUrl);//19  哒哒
        urlList.add(caipiaoUrl);//20  彩票
        urlList.add(meinvUrl);//21   美女*/
        return urlList;
    }
}
