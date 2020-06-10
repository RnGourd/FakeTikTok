package com.example.FakeTikTok;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {
    private Button buttonPlay;
    private Button buttonPause;
    public VideoView videoView;
    public ImageView imageView; //加了点怪浪怪浪的东西
    private SeekBar seekBar;
    private int sec;
    private boolean isStart;
    private int orientation;
    private boolean paused=false;
    public static VideoActivity vactivity;
    private GestureDetector gestureDetector;

    public VideoActivity() {
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vactivity = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//固定屏幕方向

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        setContentView(R.layout.activity_video);
        videoView = findViewById(R.id.videoView);
        videoView.setVideoPath(url);
        imageView = findViewById(R.id.imageView);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        videoView.setLayoutParams(layoutParams);

        gestureDetector = new GestureDetector(this, new GestureListener());//启动手势监听

        if (savedInstanceState != null){
            Log.d("saved", "success");

            int sec1 = (int) savedInstanceState.getLong("time");
            Log.d("saved", String.valueOf(sec1));
            videoView.seekTo(sec1);
            boolean isStart1 = savedInstanceState.getBoolean("play");
            if (isStart1 == true) {
                videoView.start();
            }
        }
        else//视频点开后自动开始播放
        {
            videoView.start();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong("time", sec);
        outState.putBoolean("play", isStart);
        Log.d("save", String.valueOf(sec));
        Log.d("save", String.valueOf(isStart));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView = findViewById(R.id.videoView);
        sec = videoView.getCurrentPosition();
        isStart = videoView.isPlaying();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event); //设置监听器，用于识别点击和滑动
    }

    private String getVideoPath(int resId) {
        return "android.resource://" + this.getPackageName() + "/" + resId;
    }
}
