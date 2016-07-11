package bean;

/**
 * Created by Administrator on 2016/7/6.
 */
public class ImageBean{
    private String setname;
    private String cover1;
    private String cover2;
    private String cover3;
    private String cover4;
    private String desc;//正文内容
    private String setid;

    public String getSetid(){
        return setid;
    }

    public void setSetid(String setid){
        this.setid = setid;
    }

    public String getDesc(){
        return desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public String getSetname(){
        return setname;
    }

    public void setSetname(String setname){
        this.setname = setname;
    }

    public String getCover1(){
        return cover1;
    }

    public void setCover1(String cover1){
        this.cover1 = cover1;
    }

    public String getCover2(){
        return cover2;
    }

    public void setCover2(String cover2){
        this.cover2 = cover2;
    }

    public String getCover3(){
        return cover3;
    }

    public void setCover3(String cover3){
        this.cover3 = cover3;
    }

    public String getCover4(){
        return cover4;
    }

    public void setCover4(String cover4){
        this.cover4 = cover4;
    }

    public ImageBean(String setname, String cover1, String cover2, String cover3, String cover4,String desc,String setid){
        this.setname = setname;
        this.cover1 = cover1;
        this.cover2 = cover2;
        this.cover3 = cover3;
        this.cover4 = cover4;
        this.desc = desc;
        this.setid = setid;
    }

    public ImageBean(){
    }
}
