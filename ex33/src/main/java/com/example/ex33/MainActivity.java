package com.example.ex33;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView[] img=new ImageView[12];//给imageview分配内存空间
    private int[] imagePath =new int[]{
            R.mipmap.img01,R.mipmap.img02,R.mipmap.img03,R.mipmap.img04,R.mipmap.img05,R.mipmap.img06,R.mipmap.img07,R.mipmap.img08,
            R.mipmap.img09,R.mipmap.img10,R.mipmap.img11,R.mipmap.img12
    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取屏幕的长宽
        Display display=getWindowManager().getDefaultDisplay();
        Point size=new Point();
        display.getSize(size);
        int width= size.x;
        int height= size.y;

        GridLayout layout = findViewById(R.id.layout);
        for (int i = 0; i < imagePath.length; i++) {
            img[i] = new ImageView(MainActivity.this);//初始化imageview
            img[i].setImageResource(imagePath[i]);
            img[i].setPadding(2,2,2,2);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width/4-20, (width/4-20)/2);//子控件告诉父控件，自己要如何布局
            img[i].setLayoutParams(params);
            layout.addView(img[i]);
        }
    }
}
