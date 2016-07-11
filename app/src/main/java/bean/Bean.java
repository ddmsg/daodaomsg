package bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class Bean{
    private String imgsrc;
    private String title;
    private List<AdsBean> adsList;
    private String imgsrc1;
    private String imgsrc2;
    private String digest;

    public String getDigest(){
        return digest;
    }

    public void setDigest(String digest){
        this.digest = digest;
    }

    public List<AdsBean> getAdsList(){
        return adsList;
    }

    public void setAdsList(List<AdsBean> adsList){
        this.adsList = adsList;
    }

    public String getImgsrc1(){
        return imgsrc1;
    }

    public void setImgsrc1(String imgsrc1){
        this.imgsrc1 = imgsrc1;
    }

    public String getImgsrc2(){
        return imgsrc2;
    }

    public void setImgsrc2(String imgsrc2){
        this.imgsrc2 = imgsrc2;
    }

    public static class AdsBean{
        private String imgsrc;
        private String title;

        public String getImgsrc(){
            return imgsrc;
        }

        public void setImgsrc(String imgsrc){
            this.imgsrc = imgsrc;
        }

        public String getTitle(){
            return title;
        }

        public void setTitle(String title){
            this.title = title;
        }
    }
    public String getImgsrc(){
        return imgsrc;
    }

    public void setImgsrc(String imgsrc){
        this.imgsrc = imgsrc;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
}
