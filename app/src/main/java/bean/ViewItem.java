package bean;

/**
 * Created by Administrator on 16-7-7.
 */
public class ViewItem{
    private String p360Uri;
    private int width;
    private int height;
    private String cover;
    private String title;

    public ViewItem(){
    }

    public ViewItem(String p360Uri, int width, int height, String cover, String title){
        this.p360Uri = p360Uri;
        this.width = width;
        this.height = height;
        this.cover = cover;
        this.title = title;
    }

    public String getP360Uri(){
        return p360Uri;
    }

    public void setP360Uri(String p360Uri){
        this.p360Uri = p360Uri;
    }

    public int getWidth(){
        return width;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public int getHeight(){
        return height;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public String getCover(){
        return cover;
    }

    public void setCover(String cover){
        this.cover = cover;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
    }
