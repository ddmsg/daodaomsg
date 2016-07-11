package MyFragment;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.administrator.netease.R;

import java.util.ArrayList;
import java.util.List;

import Myadapter.lv_adapter;
import Utils.OkHttp;
import bean.Bean;
import bean.UrlString;
import parse.Parse;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment1 extends Fragment{
    private int page = 0;
    private OnFragmentInteractionListener mListener;
    private ViewPager vp;
    private List<String> urlslist;
    private ListView lv;
    private int pos;

    public static BlankFragment1 newInstance(int index){
        BlankFragment1 fragment = new BlankFragment1();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    int index;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            index = getArguments().getInt("index");
        }
    }
    private List<Bean> Beanlist = new ArrayList<>();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            final List<Bean> list = (List<Bean>) msg.obj;
            Beanlist.addAll(list);
            final lv_adapter adapter = new lv_adapter(Beanlist, getContext());
            lv.setAdapter(adapter);
            lv.setOnScrollListener(new AbsListView.OnScrollListener(){
                int states=-1;
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState){
                    if(scrollState==SCROLL_STATE_IDLE){
                        states=1;
                    }else {
                        states=-1;
                    }
                }
                ProgressBar progressBar=new ProgressBar(getContext());
                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount){
                    if((firstVisibleItem+visibleItemCount)==totalItemCount&&states==1&&list!=null){

                        if(lv.getFooterViewsCount()==0){
                        lv.addFooterView(progressBar);
                        }
                        progressBar.setVisibility(View.VISIBLE);
                        page+=20;
                        pos = Beanlist.size();
                        initdata(urlslist);
                       // adapter.notifyDataSetChanged();
                        lv.setSelection(pos);

                    }
                }
            });
            srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
                @Override
                public void onRefresh(){
                    initdata(urlslist);
                    adapter.notifyDataSetChanged();
                    srl.setRefreshing(false);

                }
            });
        }

    };
    private SwipeRefreshLayout srl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment1, container, false);
        lv = (ListView) view.findViewById(R.id.listView1);
        srl=(SwipeRefreshLayout)view.findViewById(R.id.srl);
        urlslist = UrlString.getUrlList();
        initdata(urlslist);
        return view;
    }

    private void initdata(final List<String> urlslist){
        new Thread(new Runnable(){
            @Override
            public void run(){
                String str = urlslist.get(index);
                String strs = str.replace("%d", page + "");
                String num1 = OkHttp.getJson(strs);
                List<Bean> toutiao = Parse.getList(num1);
                Message message = handler.obtainMessage();
                message.obj = toutiao;
                handler.sendMessage(message);
            }
        }).start();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri){
        if(mListener != null){
            mListener.onFragmentInteraction(uri);
        }
    }

 /*   @Override
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

    @Override
    public void onDestroy(){
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
