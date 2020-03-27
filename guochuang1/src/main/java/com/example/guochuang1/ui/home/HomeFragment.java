package com.example.guochuang1.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.guochuang1.FoodActivity;
import com.example.guochuang1.MoreActivity;
import com.example.guochuang1.R;
import com.example.guochuang1.ServerActivity;
import com.example.guochuang1.SetActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Banner myBanner;
    List<Integer> imageUrlData;
    List<String> contentData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);//进行HomeViewModel的获取

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //底部导航栏
        //final TextView textView = root.findViewById(R.id.text_home);
        final TextView tv_title = getActivity().findViewById(R.id.tv_title);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
        //设置自定义顶部导航栏文字
                tv_title.setText("首页");
                tv_title.setGravity(Gravity.CENTER);
                //textView.setText(s);
            }
        });
        //轮播
        myBanner = root.findViewById(R.id.banner);
        initBanner();
        //点击事件
        root.findViewById(R.id.set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), SetActivity.class);
                getActivity().startActivity(intent);
                //Toast.makeText(getActivity(), "维护中", Toast.LENGTH_SHORT).show();
            }
        });root.findViewById(R.id.food).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), FoodActivity.class);
                getActivity().startActivity(intent);
                //Toast.makeText(getActivity(), "维护中", Toast.LENGTH_SHORT).show();
            }
        });root.findViewById(R.id.book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "暂未开放", Toast.LENGTH_SHORT).show();
            }
        });root.findViewById(R.id.server).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), ServerActivity.class);
                getActivity().startActivity(intent);
                //Toast.makeText(getActivity(), "维护中", Toast.LENGTH_SHORT).show();
            }
        });
        root.findViewById(R.id.main_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), MoreActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return root;
    }

//    public void onClick(View view) {
////        Toast.makeText(getActivity(), "维护中", Toast.LENGTH_SHORT);
////    }Fragment不是布局器，不具备渲染视图的能力


    //轮播
    private void initBanner() {
        imageUrlData = new ArrayList<>();
        contentData = new ArrayList<>();
        imageUrlData.add(R.drawable.ban_main1);
        imageUrlData.add(R.drawable.ban_main2);
        imageUrlData.add(R.drawable.ban_main3);
        imageUrlData.add(R.drawable.ban_main4);
        contentData.add("店面");
        contentData.add("座位");
        contentData.add("留言板");
        contentData.add("窗景");
        myBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        myBanner.setImageLoader(new MyLoader());
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
            Glide.with(getActivity()).load(path).into(imageView);
        }
    }


}
