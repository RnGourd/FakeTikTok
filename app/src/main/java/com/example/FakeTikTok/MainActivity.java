package com.example.FakeTikTok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//保持竖屏
        GetJson gj = new GetJson();
        JSONArray jsonArray = gj.getJson();//获取json文件

        List<VideoInfo> videoInfos = new ArrayList<VideoInfo>();
        for(int i = 0; i < jsonArray.length(); i++){
            try {
                JSONObject temp = jsonArray.getJSONObject(i);

                String feedurl = temp.getString("feedurl");
                String nickname = temp.getString("nickname");
                String description = temp.getString("description");
                String likecount = temp.getString("likecount");
                String avatar = temp.getString("avatar");

                VideoInfo video = new VideoInfo(feedurl, nickname, description, likecount, avatar); //该结构体包含了视频所有必需信息

                videoInfos.add(video);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ViewPager2 vp = findViewById(R.id.vp);
        vp.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(videoInfos, this);
        vp.setAdapter(viewPagerAdapter);

    }
}



