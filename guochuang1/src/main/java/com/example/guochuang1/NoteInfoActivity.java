package com.example.guochuang1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteInfoActivity extends AppCompatActivity {
    private final String TAG = "NoteInfoActivity";
    TextView title;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_info);

        final Intent intent=getIntent();
        String str=intent.getStringExtra("id");
        final int id;
        int id1;
        try {
            id1 =Integer.valueOf(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            id1 =-1;
        }
        id = id1;
        final EditText editText = findViewById(R.id.note_edit);
        DB_Manger_note db_manger_note = new DB_Manger_note(NoteInfoActivity.this);
        Log.i(TAG, "onCreate: id:"+id);
        final Note note = db_manger_note.findById(id);
        try {
            editText.setText(note.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        editText.requestFocus();
        editText.setSelection(editText.getText().length());
        //仅去掉标题栏，系统状态栏还是会显示
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        title = findViewById(R.id.tv_title1);
        title.setText("便签");
        title.setGravity(Gravity.CENTER);
        back = findViewById(R.id.ib_title_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日HH时mm分");
                DB_Manger_note db_manger_note1 = new DB_Manger_note(NoteInfoActivity.this);
                if (id == -1) {
                    //新增：添加
                    Note note1 = new Note();
                    note1.setContent(editText.getText().toString());
                    note1.setTime(sdf.format(new Date()));
                    db_manger_note1.add(note1);
                } else {
                    //修改：保存
                    Note note1 = new Note();
                    note1.setContent(editText.getText().toString());
                    note1.setTime(sdf.format(new Date()));
                    note1.setId(id);
                    db_manger_note1.update(note1);
                }
                NoteInfoActivity.this.finish();
                //广播刷新
                Intent intent1 = new Intent();
                intent.setAction("NoteListActivity");
                sendBroadcast(intent1);
            }
        });


    }
}
