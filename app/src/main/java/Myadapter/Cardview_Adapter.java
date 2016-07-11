package Myadapter;

import android.content.Context;
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
 * Created by Administrator on 16-7-8.
 */
public class Cardview_Adapter extends BaseAdapter{
    private List<Bean> list;
    private Context context;
    private LayoutInflater inflater;
    public final int noimg = 1;
    public final int img = 2;

    public Cardview_Adapter(List<Bean> list, Context context){
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public int getViewTypeCount(){
        return 3;
    }

    @Override
    public int getItemViewType(int position){
        Bean bean = list.get(position);
        if(bean.getImgsrc() != null){
            return img;
        }else{
            return noimg;
        }
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
        ViewHolder viewHolder = null;
        ImgViewHolder imgViewHolder = null;
        int viewtype = getItemViewType(position);
        if(convertView == null){
            switch(viewtype){
                case img:
                    convertView = inflater.inflate(R.layout.cardview_layout2, parent, false);
                    imgViewHolder = new ImgViewHolder();
                    imgViewHolder.tv2 = (TextView) convertView.findViewById(R.id.tv_cardview2);
                    imgViewHolder.iv2 = (ImageView) convertView.findViewById(R.id.iv_cardview2);
                    convertView.setTag(imgViewHolder);
                    break;
                case noimg:
                    convertView = inflater.inflate(R.layout.card_view, parent, false);
                    viewHolder = new ViewHolder();
                    viewHolder.tv = (TextView) convertView.findViewById(R.id.tv_cardview);
                    convertView.setTag(viewHolder);
                    break;
            }
        }else{
            switch(viewtype){
                case img:
                    imgViewHolder = (ImgViewHolder) convertView.getTag();
                    break;
                case noimg:
                    viewHolder = (ViewHolder) convertView.getTag();
                    break;
            }
        }
        Bean bean = list.get(position);
        switch(viewtype){
            case img:
                Picasso.with(context).load(bean.getImgsrc()).into(imgViewHolder.iv2);
                imgViewHolder.tv2.setText(bean.getDigest());
                break;
            case noimg:
                viewHolder.tv.setText(bean.getDigest());
                break;
        }
        return convertView;
    }

    class ViewHolder{
        TextView tv;
    }

    class ImgViewHolder{
        ImageView iv2;
        TextView tv2;
    }
}
