package com.example.FakeTikTok;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

import static java.lang.Thread.sleep;


public class GestureListener extends GestureDetector.SimpleOnGestureListener {

    GestureListener()
    {

    }
    private AlphaAnimation alphaAnimation;
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) //单击暂停和恢复播放
    {
        if (VideoActivity.vactivity.videoView.isPlaying()){ //判断当前播放状态
            VideoActivity.vactivity.videoView.pause();
            Toast toast=Toast.makeText(VideoActivity.vactivity, "暂停", Toast.LENGTH_SHORT);
            //显示暂停
            toast.show();
        }else {
            VideoActivity.vactivity.videoView.start();
            Toast toast=Toast.makeText(VideoActivity.vactivity, "播放", Toast.LENGTH_SHORT);
            //显示播放
            toast.show();
        }
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) //双击出现怪浪怪浪的东西
    {
        Toast toast=Toast.makeText(VideoActivity.vactivity, "2341，哇浪", Toast.LENGTH_SHORT);
        //双击显示怪怪的信息
        toast.show();
        //设置双击时弹出的图片
        VideoActivity.vactivity.imageView.setImageResource(R.drawable.walang);
        VideoActivity.vactivity.imageView.setVisibility(View.VISIBLE);
        //设置弹出图片的动画效果
        VideoActivity.vactivity.imageView.setAlpha(0.8f);
        alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(1000);
        VideoActivity.vactivity.imageView.setAnimation(alphaAnimation);
        alphaAnimation.start();
        VideoActivity.vactivity.imageView.setVisibility(View.INVISIBLE);

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}
