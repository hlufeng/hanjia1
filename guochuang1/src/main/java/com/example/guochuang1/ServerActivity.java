package com.example.guochuang1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ServerActivity extends AppCompatActivity {
    TextView title;
    ImageButton back;
    Banner myBanner;
    List<Integer> imageUrlData;
    List<String> contentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        //仅去掉标题栏，系统状态栏还是会显示
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        title=findViewById(R.id.tv_title1);
        title.setText("服务");
        title.setGravity(Gravity.CENTER);
        back=findViewById(R.id.ib_title_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerActivity.this.finish();
            }
        });

        findViewById(R.id.server_tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ServerActivity.this)
                        .setTitle("打印服务")
                        .setMessage("请前往C区进行资料打印")
                        .setNegativeButton("确定",null).show();
            }
        });findViewById(R.id.server_tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ServerActivity.this, "暂未开放", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(ServerActivity.this, ServerInfoActivity.class);
                intent.putExtra("type", "音乐");
                startActivity(intent);
            }
        });findViewById(R.id.server_tv3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ServerActivity.this)
                        .setTitle("充电服务")
                        .setMessage("请前往前台租借充电宝，使用后请及时归还")
                        .setNegativeButton("确定",null).show();
            }
        });findViewById(R.id.server_tv4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ServerActivity.this)
                        .setTitle("加热服务")
                        .setMessage("请前往D区进行自助加热")
                        .setNegativeButton("确定",null).show();
            }
        });findViewById(R.id.server_tv5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ServerActivity.this, "暂未开放", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent();
//                intent.setClass(ServerActivity.this, ServerInfoActivity.class);
//                intent.putExtra("type", "小壳出品");
//                startActivity(intent);
            }
        });
        //轮播
        myBanner = findViewById(R.id.server_banner);
        initBanner();
    }

    //轮播
    private void initBanner() {
        imageUrlData = new ArrayList<>();
        contentData = new ArrayList<>();
        imageUrlData.add(R.drawable.ban_server1);
        imageUrlData.add(R.drawable.ban_server2);
        imageUrlData.add(R.drawable.ban_server3);
        imageUrlData.add(R.drawable.ban_server4);
        contentData.add("充电宝");
        contentData.add("打印机");
        contentData.add("微波炉");
        contentData.add("饮水机");
        myBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        myBanner.setImageLoader(new ServerActivity.MyLoader());
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
            Glide.with(ServerActivity.this).load(path).into(imageView);
        }
    }
}
