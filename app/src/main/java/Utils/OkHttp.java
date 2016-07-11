package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/6.
 */
public class OkHttp{
    //传入的参数是url网址的集合
    public static List<String> getJsonList(List<String> urlList){
        final List<String> jsons = new ArrayList<String>();
        OkHttpClient client = new OkHttpClient();
        for(int i = 0; i < urlList.size(); i++){
            Request request = new Request.Builder().url(urlList.get(i)).build();
            Call call = client.newCall(request);
            call.enqueue(new Callback(){
                @Override
                public void onFailure(Call call, IOException e){

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException{
                    if(response.isSuccessful()){
                        String jsonString = response.body().string().toString();
                        jsons.add(jsonString);
                    }
                }
            });
        }
        return jsons;
    }
    public static String getJson(String url){
        try{
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            StringBuffer sbf = new StringBuffer();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String str;
            while((str = bfr.readLine())!=null){
                sbf.append(str);
            }
            return sbf.toString();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
