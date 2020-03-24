package com.example.guochuang1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NoteListActivity extends AppCompatActivity {
    TextView title;
    ImageButton back;
    private ArrayList<HashMap<String, Object>> listItem;
    private SimpleAdapter listItemAdapter;
    ImageView imageView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
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
                NoteListActivity.this.finish();
            }
        });

        init();

        //添加便签
        imageView = findViewById(R.id.note_add);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NoteListActivity.this, NoteInfoActivity.class);
                startActivity(intent);
            }
        });
        //修改便签
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(NoteListActivity.this, NoteInfoActivity.class);
                intent.putExtra("id", listItem.get(position).get("id").toString());
                startActivity(intent);
            }
        });
        //删除便签
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(NoteListActivity.this)
                        .setMessage("是否删除该条便签？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int id = Integer.valueOf(listItem.get(position).get("id").toString());
                                listItem.remove(position);
                                listItemAdapter.notifyDataSetChanged();
                                DB_Manger_note db_manger_note1 = new DB_Manger_note(NoteListActivity.this);
                                db_manger_note1.delete(id);
                                Toast.makeText(NoteListActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", null).show();

                return true;
            }
        });
    }

    public void init() {
        listView = findViewById(R.id.note_list);
        DB_Manger_note db_manger_note = new DB_Manger_note(this);
        List<Note> notes = db_manger_note.listAll();
        listItem = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日HH时mm分");
        for (Note note : notes) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("time", note.getTime());
            String content = note.getContent();
            if (content.length() > 20) {
                content = content.substring(0, 20) + "...";
            }
            map.put("content", content);
            map.put("id", note.getId());
            listItem.add(map);
        }
        listItemAdapter = new SimpleAdapter(this, listItem, R.layout.note_item,
                new String[]{"time", "content"},
                new int[]{R.id.note_time, R.id.note_content});
        listView.setAdapter(listItemAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }


}
