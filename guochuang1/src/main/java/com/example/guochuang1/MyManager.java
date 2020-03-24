package com.example.guochuang1;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MyManager extends AppCompatActivity {
    TextView title;
    ImageButton back;
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_manager);
        //仅去掉标题栏，系统状态栏还是会显示
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        title = findViewById(R.id.tv_title1);
        title.setText("超级管理");
        title.setGravity(Gravity.CENTER);
        back = findViewById(R.id.ib_title_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyManager.this.finish();
            }
        });

        btn1=findViewById(R.id.mag_button);
        btn2=findViewById(R.id.mg_button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBManger_Set dbManger_set = new DBManger_Set(MyManager.this);
                dbManger_set.deleteAll();
                Toast.makeText(MyManager.this,"清空成功！",Toast.LENGTH_SHORT).show();
            }
        });btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB_Manger_food db_manger_food = new DB_Manger_food(MyManager.this);
                db_manger_food.deleteAll();
                Toast.makeText(MyManager.this,"清空成功！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
