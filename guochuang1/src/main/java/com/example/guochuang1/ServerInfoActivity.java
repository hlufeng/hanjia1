package com.example.guochuang1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ServerInfoActivity extends AppCompatActivity {
    TextView title;
    ImageButton back;
    WebView webView;
    String head,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_info);
        //仅去掉标题栏，系统状态栏还是会显示
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        Intent intent=getIntent();
        head=intent.getStringExtra("type");
        title=findViewById(R.id.tv_title1);
        title.setText(head);
        title.setGravity(Gravity.CENTER);
        back=findViewById(R.id.ib_title_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerInfoActivity.this.finish();
            }
        });

        webView = findViewById(R.id.server_web);
        //url="https://music.163.com/";
        url = "https://www.1ting.com/";
        //加载服务器上的页面
        webView.loadUrl(url);
        //内嵌App，不以浏览器的方式打开
        webView.setWebViewClient(new WebViewClient());
        //获取浏览器设置
        WebSettings webSettings = webView.getSettings();
        //允许javascript
        webSettings.setJavaScriptEnabled(true);


    }
}
