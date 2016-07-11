/*
package Myadapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.netease.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Bean.ManyTitleBean;

*/
/**
 * Created by Administrator on 16-7-4.
 *//*

public class adapter_recyclerview extends RecyclerView.Adapter{
    private List<ManyTitleBean>list;
    private Context context;
    private LayoutInflater inflater;
    private int ADS=1;//头布局
    private int NORMOAL=2;//普通布局
    private int MOREPICTURE=3;//三图布局
    public adapter_recyclerview(List<ManyTitleBean> list, Context context){
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View views=null;
        if(viewType == ADS){
            views=inflater.inflate(R.layout.recyclerview1c,parent,false);
        }else if(viewType == NORMOAL){
            views=inflater.inflate(R.layout.recyclerview1b,parent,false);
        }else if(viewType == MOREPICTURE){
            views=inflater.inflate(R.layout.recyclerview1d,parent,false);
        }
            MyViewHolder viewHolder=new MyViewHolder(views);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
        int type=holder.getItemViewType();
        if(type==ADS){
            List<ManyTitleBean.AdsBean>adslist=list.get(position).getAds();
            Pager_adapter adapter = new Pager_adapter(adslist,context);
            ((MyViewHolder) holder).vp_frg3.setAdapter(adapter);
        }else if(type==NORMOAL){
            String urls=list.get(position).getImgsrc();
            Picasso.with(context).load(urls).into(((MyViewHolder) holder).iv_frg1);
            ((MyViewHolder) holder).tv_frg1.setText(list.get(position).getTitle());
        }else {
            ((MyViewHolder) holder).tv_frg4.setText(list.get(position).getTitle());
            Picasso.with(context).load(list.get(position).getImgsrc()).into(((MyViewHolder) holder).iv_1d);
            Picasso.with(context).load(list.get(position).getImgextra().get(0).getImgsrc()).into(((MyViewHolder) holder).iv_2d);
            Picasso.with(context).load(list.get(position).getImgextra().get(0).getImgsrc()).into(((MyViewHolder) holder).iv_3d);
        }
    }

    @Override
    public int getItemViewType(int position){
        if(list.get(position).getAds() != null){
            return ADS;
        }else if(list.get(position).getImgextra() != null){
            return MOREPICTURE;
        }else {
            return NORMOAL;
        }
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private final ImageView iv_frg1;
        private final TextView tv_frg1;
        private final ViewPager vp_frg3;
        private final ImageView iv_1d;
        private final ImageView iv_2d;
        private final ImageView iv_3d;
        private final TextView tv_frg4;
        public MyViewHolder(View itemView){
            super(itemView);
            //只有一个图片的布局
            iv_frg1 = (ImageView) itemView.findViewById(R.id.iv_frg1a);
            tv_frg1 = (TextView) itemView.findViewById(R.id.tv_frg1a);
            //头部布局
            vp_frg3 = (ViewPager) itemView.findViewById(R.id.vp_frg3);
            //三张图片的布局
            iv_1d = (ImageView) itemView.findViewById(R.id.iv_frg1d);
            iv_2d = (ImageView) itemView.findViewById(R.id.iv_frg2d);
            iv_3d = (ImageView) itemView.findViewById(R.id.iv_frg3d);
            tv_frg4 = (TextView) itemView.findViewById(R.id.tv_frg1d);
        }
    }
}
*/
