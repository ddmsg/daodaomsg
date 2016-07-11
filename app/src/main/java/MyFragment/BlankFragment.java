package MyFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.netease.LoginActivity;
import com.example.administrator.netease.R;
import com.example.administrator.netease.VideoActivity;

import java.util.ArrayList;
import java.util.List;

import Myadapter.Cardview_Adapter;
import Myadapter.Title_adapter;
import Myadapter.Video_adapter;
import Utils.DividerItemDecoration;
import Utils.OkHttp;
import bean.Bean;
import bean.UrlString;
import parse.Parse;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment{

    private OnFragmentInteractionListener mListener;
    private ViewPager vp;
    private TabLayout tablayout;
    private Button btn_login;
    private List<bean.ViewItem> videoList;
    private Context context;

    public static BlankFragment newInstance(int index, String param2){
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        args.putString("urls", param2);
        fragment.setArguments(args);
        return fragment;
    }

    int num;
    String url;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            num = getArguments().getInt("index");
            url = getArguments().getString("urls");
        }
        context = getContext();
    }

    View view;
    String[] titles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //每个不同的Fragment加载不同的布局
        view = null;
        if(num == 1){
            view = inflater.inflate(R.layout.fragment_blank, container, false);
        }else if(num == 2){
            view = inflater.inflate(R.layout.fragment_blank2, container, false);
        }else if(num == 3){
            view = inflater.inflate(R.layout.fragment_blank3, container, false);
        }else if(num == 4){
            view = inflater.inflate(R.layout.fragment_blank4, container, false);
        }else if(num == 5){
            view = inflater.inflate(R.layout.fragment_blank5, container, false);
        }
        initview();
        return view;
    }

    List<Fragment> fragments;

    private void initview(){
        if(num == 1){
            tablayout = (TabLayout) view.findViewById(R.id.tablayout);
            fragments = new ArrayList<>();
            vp = (ViewPager) view.findViewById(R.id.vp);
            titles = new String[]{"头条", "娱乐", "热点", "体育", "财经", "科技", "汽车", "时尚"};
            for(int i = 0; i < titles.length; i++){
                BlankFragment1 fragment = BlankFragment1.newInstance(i);
                fragments.add(fragment);
            }
            Title_adapter ad = new Title_adapter(getFragmentManager(), titles, fragments);
            vp.setAdapter(ad);
            tablayout.setupWithViewPager(vp);
        }else if(num == 5){
            //用户在列表栏中点击了“我”选项，弹出用户操作界面
            List<String> myList = new ArrayList<>();
            btn_login = (Button) view.findViewById(R.id.my_login);
            ListView mine_ListView = (ListView) view.findViewById(R.id.mine_listView);
            String[] myListString = {"我的消息", "金币商城", "金币任务", "我的钱包", "夜间模式", "离线阅读", "活动广场", "我的邮箱", "意见反馈"};
            for(int i = 0; i < myListString.length; i++){
                myList.add(myListString[i]);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, myList);
            mine_ListView.setAdapter(adapter);
            btn_login.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    startActivityForResult(new Intent(getContext(), LoginActivity.class), 1);
                }
            });
        }else if(num == 2){
            //视频页面
            RecyclerView video_recycview = (RecyclerView) view.findViewById(R.id.video_recycview);
            String videoString = Parse.GetVideoString(context);//取得视频相关的json数据
            //解析得到的json数据
            videoList = Parse.ParseVideoString(videoString);
            Video_adapter adapter = new Video_adapter(context, videoList);//把解析得到的viewitem集合传入适配器
            adapter.setOnItemClickListener(new Video_adapter.OnItemClickListener(){
                @Override
                public void OnItemClick(int position){
                    //item点击事件,跳转至播放页面,需要把视频地址和文本内容传输过去
                    Intent intent = new Intent(context, VideoActivity.class);
                    intent.putExtra("video_url", videoList.get(position).getP360Uri());
                    intent.putExtra("video_text", videoList.get(position).getTitle());
                    startActivity(intent);
                }
            });
            GridLayoutManager manager = new GridLayoutManager(context, 2);
            //给recycview每个item间划分割线
            video_recycview.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL_LIST));
            video_recycview.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
            video_recycview.setLayoutManager(manager);
            video_recycview.setAdapter(adapter);
        }else if(num == 3){
           lv = (ListView) view.findViewById(R.id.listView3);
           srl = (SwipeRefreshLayout) view.findViewById(R.id.srl3);
            initdata();
            srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
                @Override
                public void onRefresh(){
                    initdata();
                    srl.setRefreshing(false);
                }
            });;
        }
    }
    ListView lv;
    SwipeRefreshLayout srl;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            List<Bean> beanlist = (List<Bean>) msg.obj;
            Cardview_Adapter ad = new Cardview_Adapter(beanlist, context);
            lv.setAdapter(ad);
        }
    };

    int page = 0;

    private void initdata(){
        new Thread(new Runnable(){
            @Override
            public void run(){
                String str = OkHttp.getJson(UrlString.duanziUrl);
                List<Bean> beanlist = Parse.getJsonForDuanzi(str);
                Message msg = handler.obtainMessage();
                msg.obj = beanlist;
                handler.sendMessage(msg);
            }
        }).start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && data != null){
            String username = data.getStringExtra("username");
            btn_login.setText(username);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri){
        if(mListener != null){
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
    /*  @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener) context;
        }else{
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach(){
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener{
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
