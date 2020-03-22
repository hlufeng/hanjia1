package com.example.guochuang1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {
    TextView title;
    ImageButton back;
    Banner myBanner;
    List<Integer> imageUrlData;
    List<String> contentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        //仅去掉标题栏，系统状态栏还是会显示
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        title=findViewById(R.id.tv_title1);
        title.setText("点餐");
        title.setGravity(Gravity.CENTER);
        back=findViewById(R.id.ib_title_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodActivity.this.finish();
            }
        });

        findViewById(R.id.food_tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(FoodActivity.this, "暂未开放", Toast.LENGTH_SHORT).show();
                //传参：主食（一个页面实现展示页面）
                Intent intent = new Intent();
                intent.setClass(FoodActivity.this, FoodListActivity.class);
                intent.putExtra("type", "主食");
                startActivity(intent);
            }
        });findViewById(R.id.food_tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(FoodActivity.this, "暂未开放", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(FoodActivity.this, FoodListActivity.class);
                intent.putExtra("type", "咖啡");
                startActivity(intent);
            }
        });findViewById(R.id.food_tv3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(FoodActivity.this, "暂未开放", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(FoodActivity.this, FoodListActivity.class);
                intent.putExtra("type", "冰淇淋");
                startActivity(intent);
            }
        });findViewById(R.id.food_tv4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(FoodActivity.this, "暂未开放", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(FoodActivity.this, FoodListActivity.class);
                intent.putExtra("type", "冰饮");
                startActivity(intent);
            }
        });findViewById(R.id.food_tv5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(FoodActivity.this, "暂未开放", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(FoodActivity.this, FoodListActivity.class);
                intent.putExtra("type", "茶饮");
                startActivity(intent);
            }
        });findViewById(R.id.food_tv6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(FoodActivity.this, "暂未开放", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(FoodActivity.this, FoodListActivity.class);
                intent.putExtra("type", "甜点");
                startActivity(intent);
            }
        });findViewById(R.id.main_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                String url = "https://cd.meituan.com/";
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        //轮播
        myBanner = findViewById(R.id.food_banner);
        initBanner();

    }

    //轮播
    private void initBanner() {
        imageUrlData = new ArrayList<>();
        contentData = new ArrayList<>();
        imageUrlData.add(R.drawable.ban_food1);
        imageUrlData.add(R.drawable.ban_food2);
        imageUrlData.add(R.drawable.ban_food3);
        imageUrlData.add(R.drawable.ban_food4);
        contentData.add("卤肉饭");
        contentData.add("手抓饼");
        contentData.add("蛋糕");
        contentData.add("珍珠奶茶");
        myBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        myBanner.setImageLoader(new FoodActivity.MyLoader());
        myBanner.setImages(imageUrlData);
        myBanner.setBannerTitles(contentData);
        myBanner.setBannerAnimation(Transformer.Default);
        //切换频率
        myBanner.setDelayTime(2000);
        //自动启动
        myBanner.isAutoPlay(true);
        //位置设置
        myBanner.setIndicatorGravity(BannerConfig.CENTER);
        //开始运行
        myBanner.start();
    }

    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(FoodActivity.this).load(path).into(imageView);
        }
    }
}
