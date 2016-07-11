package Myadapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.netease.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import bean.Bean;

/**
 * Created by Administrator on 16-7-6.
 */
public class lv_adapter extends BaseAdapter{
    private List<Bean> list;
    private Context context;
    private LayoutInflater inflater;
    private final int ADS=1;//头布局
    private final int NORMOAL=2;//普通布局
    private final int MOREPICTURE=3;//三图布局

    public lv_adapter(List<Bean> list, Context context){
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        AdsViewHolder adsViewHolder = null;
        GeneralViewHolder generalViewHolder = null;
        PhotoViewHolder photoViewHolder = null;
        //获取当前要加载的item的类型，是广告栏还是照片集还是普通item
        int itemViewType = getItemViewType(position);
        if (convertView == null) {
            switch (itemViewType) {
                case ADS:
                    convertView = inflater.inflate(R.layout.recyclerview1c, parent, false);
                    adsViewHolder = new AdsViewHolder();
                    adsViewHolder.viewPager = (ViewPager) convertView.findViewById(R.id.view_fuck);
                    convertView.setTag(adsViewHolder);
                    break;
                case NORMOAL:
                    convertView = inflater.inflate(R.layout.recyclerview1a, parent, false);
                    generalViewHolder = new GeneralViewHolder();
                    generalViewHolder.title = (TextView) convertView.findViewById(R.id.tv_frg1a);
                    generalViewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_frg1a);
                    convertView.setTag(generalViewHolder);
                    break;
                case MOREPICTURE:
                    convertView = inflater.inflate(R.layout.recyclerview1d, parent, false);
                    photoViewHolder = new PhotoViewHolder();
                    photoViewHolder.iv1 = (ImageView) convertView.findViewById(R.id.iv_frg1d);
                    photoViewHolder.iv2 = (ImageView) convertView.findViewById(R.id.iv_frg2d);
                    photoViewHolder.iv3 = (ImageView) convertView.findViewById(R.id.iv_frg3d);
                    photoViewHolder.title = (TextView) convertView.findViewById(R.id.tv_frg1d);
                    convertView.setTag(photoViewHolder);
                    break;
            }
        } else {
            switch (itemViewType) {
                case ADS:
                    adsViewHolder = (AdsViewHolder) convertView.getTag();
                    break;
                case NORMOAL:
                    generalViewHolder = (GeneralViewHolder) convertView.getTag();
                    break;
                case MOREPICTURE:
                    photoViewHolder = (PhotoViewHolder) convertView.getTag();
                    break;
            }
        }
        final Bean newsBean = list.get(position);
        switch (itemViewType) {
            case ADS:
                Pager_adapter adapter = new Pager_adapter(newsBean.getAdsList(),context);
                adsViewHolder.viewPager.setAdapter(adapter);
                break;
            case NORMOAL:
                generalViewHolder.title.setText(newsBean.getTitle());
                Picasso.with(context).load(newsBean.getImgsrc()).into(generalViewHolder.imageView);
                break;
            case MOREPICTURE:
                Picasso.with(context).load(newsBean.getImgsrc()).into(photoViewHolder.iv1);
                Picasso.with(context).load(newsBean.getImgsrc1()).into(photoViewHolder.iv2);
                Picasso.with(context).load(newsBean.getImgsrc2()).into(photoViewHolder.iv3);
                photoViewHolder.title.setText(newsBean.getTitle());
                break;
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position){
        Bean newsBean = list.get(position);
        if (newsBean.getAdsList() != null) {
            return ADS;
        } else if (newsBean.getImgsrc1()!=null&&newsBean.getImgsrc2() != null) {
            return MOREPICTURE;
        } else {
            return NORMOAL;
        }
    }

    @Override
    public int getViewTypeCount(){
        return 5;
    }

    class AdsViewHolder {
        ViewPager viewPager;
    }

    class GeneralViewHolder {
        ImageView imageView;
        TextView title;
    }

    class PhotoViewHolder {
        ImageView iv1, iv2, iv3;
        TextView title;
    }
}
