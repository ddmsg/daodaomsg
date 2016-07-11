package Myadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.netease.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import bean.ViewItem;

/**
 * Created by Administrator on 16-7-6.
 */
public class Video_adapter extends RecyclerView.Adapter{
    private Context context;
    private List<ViewItem> list;
    private LayoutInflater inflater;
    public Video_adapter(Context context, List<ViewItem> list){
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.video_layout,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
        //设置上每个item的图片和文字
        Picasso.with(context).load(list.get(position).getCover()).into(((MyViewHolder) holder).imageView);
        ((MyViewHolder) holder).textView.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount(){
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public MyViewHolder(View itemView){
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.video_image);
            textView = (TextView) itemView.findViewById(R.id.video_text);
            //itemview设置点击监听
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    //点击当前位置的item，参数为得到的当前position
                    listener.OnItemClick(getLayoutPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v){
                    listener.OnItemClick(getLayoutPosition());
                    return false;
                }
            });
        }

    }

    /*给recycleView自定义item的点击事件的思路，在MyViewHolder加载item的时候，让MyViewHolder实现
    * View类中的onclick事件，在onclick事件实现onitemclick监听*/
    public interface OnItemClickListener{
        //把当前点击的position位置传入
        void OnItemClick(int position);
    }
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
