package com.example.guochuang1;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MoreActivity extends AppCompatActivity {
    ImageButton back;
    TextView title, more_tv, more_tv2, more_tv3, more_tv4, more_tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        //仅去掉标题栏，系统状态栏还是会显示
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        title = findViewById(R.id.tv_title1);
        title.setText("小壳寄语");
        title.setGravity(Gravity.CENTER);
        back = findViewById(R.id.ib_title_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoreActivity.this.finish();
            }
        });

        String str1 = "\u3000\u3000世界看着很热闹，但却越来越让人孤独；我们看着很热闹地活着，但却依然孤独。" +
                "物质之“空”易解，心灵之“空”难决，孤独是一种沉淀，孤独沉淀后的思维是清明。“安静的壳子”就是为了帮助青年人在破碎重整中找回自我，获得心灵的满足。一起来看看吧" +
                "\n\u3000\u3000安静的壳子，是主要针对大学生和职场人员的一款线上与线下相结合的实体创业项目。线上平台主要包括线上的预约、反馈、VIP制度、贵宾DIY" +
                "体验等；线下主要是提供实际服务。主营业务分为两方面，一方面为“休闲壳子”、另一方面是“美食壳子”，壳子尝试充分利用互联网和实体业态相结合为这一群体提供一个舒适、私密、不被外人打扰的场所。" +
                "\n\u3000\u3000我们这里主要介绍休闲壳子，我们提供独处空间，各位小主，如果担心自己自制力不够，控制不住想玩手机。可以把手机放在手机袋里，让自己完全沉浸在学习中。";

        String str2 = "\u3000\u3000来到“安静的壳子“，各位小主，都会有一个专属于自己的空间，我们的空间分不同种类，你可以随意选择，只要你喜欢便好。";
        String str3 = "\u3000\u3000这是完全隐秘的壳子。在这里，你想干什么就干什么。如果你想解压，想放松，想拥有私人空间，就来这里吧。";
        String str4 =
                "\u3000\u3000这是大家一起奋斗的壳子，在这里，你可以约上几个朋友一起学习，也可以跟隔壁并不熟悉的小哥哥或者小姐姐一起奋笔疾书。如果你想学习、工作或者想提升自己，那就来这里吧！";
        String str5 = "\u3000\u3000安静的壳子”还为各位小主们准备了打印机、充电宝、饮水机、茶叶、小零食、微波炉。给各位小壳子们极致的服务体验。" +
                "\n\u3000\u3000" +
                "在美食壳子，我们精心制作的食物，让你在心灵得到满足的同时，味蕾也能得到满足。这里有提神的咖啡，甜甜的奶茶，精致的甜点，爽口的面食，香喷喷的米饭。赶快在app" +
                "上预约吧。";
        more_tv = findViewById(R.id.more_tv);
        more_tv2 = findViewById(R.id.more_tv2);
        more_tv3 = findViewById(R.id.more_tv3);
        more_tv4 = findViewById(R.id.more_tv4);
        more_tv5 = findViewById(R.id.more_tv5);


        more_tv.setText(str1);
        more_tv2.setText(str2);
        more_tv3.setText(str3);
        more_tv4.setText(str4);
        more_tv5.setText(str5);

    }
}
