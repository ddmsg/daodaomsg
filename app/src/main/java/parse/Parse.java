package parse;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import bean.Bean;
import bean.ImageBean;
import bean.ViewItem;

/**
 * Created by Administrator on 2016/7/6.
 */
public class Parse{

    //该方法是解析assets文件的json数据，返回一个viewitem的集合
    public static List<ViewItem> ParseVideoString(String json){
        List<ViewItem> list = new ArrayList<>();
        try{
            JSONArray data = new JSONObject(json).getJSONObject("data").getJSONArray("data");
            for(int i = 0; i < data.length(); i++){
                try{
                    JSONObject group = data.getJSONObject(i).getJSONObject("group");
                    JSONArray url_list = group.getJSONObject("360p_video").getJSONArray("url_list");
                    String p360url = url_list.getJSONObject(0).getString("url");
                    String cover = group.getJSONObject("medium_cover").getJSONArray("url_list").getJSONObject(0).getString("url");
                    int width = group.getInt("video_width");
                    int height = group.getInt("video_height");
                    String title = group.getString("title");
                    list.add(new bean.ViewItem(p360url,width,height,cover,title));
                }catch(JSONException e){
                    //  e.printStackTrace();
                }
            }
            return list;
        }catch(JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    //此方法是读取assets文件下的“video1.java”的数据，返回的是一串json数组
    public static String GetVideoString(Context context){
        AssetManager assetManager = context.getAssets();
        try{
            InputStream is = assetManager.open("video1.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer sbf = new StringBuffer();
            String str;
            while((str = br.readLine()) != null){
                sbf.append(str);
            }
            return sbf.toString();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public static List<Bean> getList(String jsonString){
        List<Bean> list = new ArrayList<>();
        try{
            JSONObject js1 = new JSONObject(jsonString);
            JSONArray ja = null;
            if(js1.has("T1348647909107")){//头条
                ja = js1.getJSONArray("T1348647909107");
            }else if(js1.has("T1348648517839")){//娱乐
                ja = js1.getJSONArray("T1348648517839");
            }else if(js1.has("推荐")){//热点
                ja = js1.getJSONArray("推荐");
            }else if(js1.has("T1348649079062")){//体育
                ja = js1.getJSONArray("T1348649079062");
            }else if(js1.has("T1348648756099")){//财经
                ja = js1.getJSONArray("T1348648756099");
            }else if(js1.has("T1348649580692")){//科技
                ja = js1.getJSONArray("T1348649580692");
            }else if(js1.has("list")){//汽车
                ja = js1.getJSONArray("list");
            }else if(js1.has("T1348650593803")){//时尚
                ja = js1.getJSONArray("T1348650593803");
            }/*else if(js1.has("")){//图片
                ja = js1.getJSONArray("");
            }*/else if(js1.has("T1350383429665")){//轻松一刻
                ja = js1.getJSONArray("T1350383429665");
            }/*else if(js1.has("")){//段子
                ja = js1.getJSONArray("");
            }*/else if(js1.has("T1348648141035")){//军事
                ja = js1.getJSONArray("T1348648141035");
            }else if(js1.has("T1368497029546")){//历史
                ja = js1.getJSONArray("T1368497029546");
            }else if(js1.has("T1348654105308")){//家居
                ja = js1.getJSONArray("T1348654105308");
            }else if(js1.has("T1370583240249")){//独家
                ja = js1.getJSONArray("T1370583240249");
            }else if(js1.has("T1348654151579")){//游戏
                ja = js1.getJSONArray("T1348654151579");
            }else if(js1.has("T1414389941036")){//健康
                ja = js1.getJSONArray("T1414389941036");
            }else if(js1.has("T1414142214384")){//政务
                ja = js1.getJSONArray("T1414142214384");
            }else if(js1.has("T1444270454635")){//漫画
                ja = js1.getJSONArray("T1444270454635");
            }else if(js1.has("T1444289532601")){//哒哒
                ja = js1.getJSONArray("T1444289532601");
            }else if(js1.has("T1356600029035")){//彩票
                ja = js1.getJSONArray("T1356600029035");
            }else if(js1.has("美女")){//美女
                ja = js1.getJSONArray("美女");
            }
            for(int i = 0; i < ja.length(); i++){
                Bean bean = new Bean();
                JSONObject data = ja.getJSONObject(i);
                String title = data.getString("title");//标题
                String imgsrc = data.getString("imgsrc");//第1张图片
                //viewPager的头布局
                if(data.has("ads")){
                    JSONArray ads = data.getJSONArray("ads");
                    List<Bean.AdsBean> adsList = new ArrayList<>();
                    for(int k = 0; k < ads.length(); k++){
                        Bean.AdsBean adsBean = new Bean.AdsBean();
                        String titleAds = ads.getJSONObject(k).getString("title");
                        String imgsrcAds = ads.getJSONObject(k).getString("imgsrc");
                        adsBean.setTitle(titleAds);
                        adsBean.setImgsrc(imgsrcAds);
                        adsList.add(adsBean);
                    }
                    bean.setAdsList(adsList);//把ads的list集合加到bean对象中去
                }
                //3张图片的item的另外2张图片
                JSONArray img = null;
                String imgsrc1 = null;
                String imgsrc2 = null;
                if(data.has("imgnewextra")){
                    img = data.getJSONArray("imgnewextra");
                    imgsrc1 = img.getJSONObject(0).getString("imgsrc");
                    imgsrc2 = img.getJSONObject(1).getString("imgsrc");
                }else if(data.has("imgextra")){
                    img = data.getJSONArray("imgextra");
                    imgsrc1 = img.getJSONObject(0).getString("imgsrc");
                    imgsrc2 = img.getJSONObject(1).getString("imgsrc");
                }
                //把数据加到bean对象中去
                bean.setTitle(title);
                bean.setImgsrc(imgsrc);
                bean.setImgsrc1(imgsrc1);
                bean.setImgsrc2(imgsrc2);
                //把对象加载List<Bean>集合中去
                list.add(bean);
            }
            return list;
        }catch(JSONException e){
            e.printStackTrace();
        }
        return null;
    }
    //段子的解析
    public static List<Bean> getJsonForDuanzi(String jsonString){
        try{
            JSONObject js1 = new JSONObject(jsonString);
            JSONArray ja = js1.getJSONArray("段子");
            List<Bean> list = new ArrayList<>();
            for(int i = 0; i < ja.length(); i++){
                Bean bean = new Bean();
                //有的段子有imgsrc，有的没有，所以要判断
                if(ja.getJSONObject(i).has("imgsrc")){
                    String imgsrc = ja.getJSONObject(i).getString("imgsrc");//图片
                    bean.setImgsrc(imgsrc);
                }
                String digest = ja.getJSONObject(i).getString("digest");//正文内容
                bean.setDigest(digest);
                list.add(bean);
            }
            return list;
        }catch(JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    //图片的解析
    public static List<ImageBean> getJsonForImage(String jsonString){
        try{
            List<ImageBean>list = new ArrayList<>();
            JSONArray ja = new JSONArray(jsonString);
            for(int i = 0; i < ja.length(); i++){
                JSONObject data = ja.getJSONObject(i);
                String setname = data.getString("setname");//图片下面的标题
                String cover1 = data.getString("cover");//第一张图片的网址（也是显示在首页的图片）,点击进第二个界面的第1张图片
                JSONArray pics = data.getJSONArray("pics");
                String cover2 = pics.getString(0);//点击进第二个界面的第2张图片
                String cover3 = pics.getString(1);//点击进第二个界面的第3张图片
                String cover4 = pics.getString(2);//点击进第二个界面的第4张图片
                String desc = data.getString("desc");//点击进入第2个界面的正文内容
                String setid = data.getString("setid");//加载新的界面时网址后面要加的字符串
                /*图片界面，下拉刷新时，把解析到的json数组中的"setid"属性添加给连接后面即可。
                http://c.3g.163.com//photo/api/list/0096/4GJ60096.json   ===》第一页
                http://c.3g.163.com//photo/api/morelist/0096/4GJ60096/98086.json   ======》下拉页*/
                ImageBean Bean = new ImageBean(setname,cover1,cover2,cover3,cover4,desc,setid);
                list.add(Bean);
            }
            return list;
        }catch(JSONException e){
            e.printStackTrace();
        }
        return null;
    }


}
