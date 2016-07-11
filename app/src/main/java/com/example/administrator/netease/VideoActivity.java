package com.example.administrator.netease;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity{

    private VideoView video_view;
    private TextView video_text;
    private ProgressDialog dialog;
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        video_view = (VideoView) findViewById(R.id.video_view);
        video_text = (TextView) findViewById(R.id.video_playing_text);
        btn_start = (Button) findViewById(R.id.btn_start);
        initData();
    }

    //给视频后文本设置内容
    private void initData(){
        dialog = ProgressDialog.show(this, "正在加载中...", "视屏马上开始");
        //获得跳转页面传输过来的意图对象
        Intent intent = getIntent();
        String url = intent.getStringExtra("video_url");
        String text = intent.getStringExtra("video_text");
        //设置上文字
        video_text.setText(text);
        Uri uri = Uri.parse(url);
        //给videoView设置上视频地址
        video_view.setVideoURI(uri);
        MediaController controller = new MediaController(this);
        video_view.setMediaController(controller);
        video_view.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mp){
                //当视屏准备完成可以播放时,销毁对话框,全屏播放
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                video_view.setLayoutParams(params);
                if(dialog != null){
                    dialog.dismiss();
                }
                video_view.start();
                video_text.setVisibility(View.GONE);
                btn_start.setVisibility(View.GONE);
            }
        });
    }

    public void clickok(View view){
        //点击开始播放
       // video_view.requestFocus();

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}
