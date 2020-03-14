package com.example.guochuang1;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.guochuang1.ui.home.HomeFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MyBanner extends Banner{

    List<Integer> imageUrlData;
    List<String> contentData;
    Activity act;

    public MyBanner(Context context) {
        super(context);
    }

    public Banner initBanner(Banner banner, List<Integer> image, List<String> content, int time, Activity activity){
        act=activity;
        imageUrlData = image;
        contentData = content;
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new MyBanner.MyLoader());
        banner.setImages(imageUrlData);
        banner.setBannerTitles(contentData);
        banner.setBannerAnimation(Transformer.Default);
        //切换频率
        banner.setDelayTime(time);
        //自动启动
        banner.isAutoPlay(true);
        //位置设置
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //开始运行
        banner.start();
        return banner;
    }

    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(act).load(path).into(imageView);
        }
    }
}
