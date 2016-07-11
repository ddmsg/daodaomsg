package Myadapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.netease.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import bean.Bean;

/**
 * Created by Administrator on 16-7-4.
 */
public class Pager_adapter extends PagerAdapter{
    private List<Bean.AdsBean>list;
    private Context context;
    public Pager_adapter(List<Bean.AdsBean> list,Context context){
        this.list = list;
        this.context=context;
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        ImageView iv=new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.with(context).load(list.get(position).getImgsrc()).error(R.mipmap.ic_launcher).into(iv);
        container.addView(iv);
        return iv;
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((View) object);
    }
}

