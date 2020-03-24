package com.example.guochuang1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class SetActivity extends AppCompatActivity {
    private final String TAG = "SetActivity";
    TextView title, date, hour, during, price_tv;
    int year, month, day, hours, mins, price, time;
    Date date_start;
    SetOrder setOrder;
    ImageButton back;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        setOrder = new SetOrder();
        price_tv = findViewById(R.id.car_bottom_tv2);
        price = 0;
        time = 0;
        //仅去掉标题栏，系统状态栏还是会显示
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        title = findViewById(R.id.tv_title1);
        title.setText("订座");
        title.setGravity(Gravity.CENTER);
        back = findViewById(R.id.ib_title_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetActivity.this.finish();
            }
        });

        date = findViewById(R.id.date);
        hour = findViewById(R.id.hour);
        during = findViewById(R.id.during);
        date_start = new Date();
        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hours = calendar.get(Calendar.HOUR);
        mins = calendar.get(Calendar.MINUTE);
        //添加监听
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(SetActivity.this, DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    // 点击“确定”时触发
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        //int a=monthOfYear + 1;
                        date_start.setYear(year - 1900);//使用date需要-1900
                        date_start.setMonth(monthOfYear);//使用date不用+1
                        date_start.setDate(dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                        String str = simpleDateFormat.format(date_start);
                        //String str = year + "年" + a + "月" + dayOfMonth + "日";
                        Toast.makeText(SetActivity.this, str, Toast.LENGTH_SHORT).show();
                        date.setText(str);
                    }
                }, year, month, day);
                dpd.show();
            }
        });
        hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tpd = new TimePickerDialog(SetActivity.this, TimePickerDialog.THEME_DEVICE_DEFAULT_LIGHT, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date_start.setHours(hourOfDay);
                        date_start.setMinutes(minute);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH时mm分");
                        String str = simpleDateFormat.format(date_start);
                        //String str = hourOfDay + "点" + minute + "分";
                        Toast.makeText(SetActivity.this, str, Toast.LENGTH_SHORT).show();
                        hour.setText(str);
                    }
                }, hours, mins, false);
                tpd.show();
            }
        });
        during.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText et = new EditText(SetActivity.this);
                et.setInputType(InputType.TYPE_CLASS_NUMBER);
                new AlertDialog.Builder(SetActivity.this).setTitle("请输入小时数")
                        .setIcon(R.drawable.ic_during)
                        .setView(et)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //按下确定键后的事件
                                try {
                                    time = Integer.valueOf(et.getText().toString());
                                    if (time > 12) {
                                        Toast.makeText(SetActivity.this, "一次使用的时间不能超过12小时", Toast.LENGTH_SHORT).show();
                                    } else if (time == 0) {
                                        Toast.makeText(SetActivity.this, "时间不能为0", Toast.LENGTH_SHORT).show();
                                    } else {
                                        String str = time + "小时";
                                        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
                                        during.setText(str);
                                        price_tv.setText("￥" + price * time);
                                        //setOrder.setDuring_time(a);
                                    }
                                } catch (NumberFormatException e) {
                                    Toast.makeText(SetActivity.this, "格式错误", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
        radioGroup = findViewById(R.id.rg);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i(TAG, "onCheckedChanged: checkedId:" + checkedId);
                switch (checkedId%3) {
                    case 1:
                        setOrder.setType("A");
                        price = 3;
                        break;
                    case 2:
                        setOrder.setType("B");
                        price = 5;
                        break;
                    case 0:
                        setOrder.setType("C");
                        price = 10;
                        break;
                }
                price_tv.setText("￥" + price * time);
            }
        });
        findViewById(R.id.car_bottom_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
                Log.i(TAG, "onClick: now:" + sdf.format(d));
                Log.i(TAG, "onClick: start_time:" + sdf.format(date_start));
                String s = date.getText().toString() + hour.getText().toString();
                try {
                    sdf.parse(s);//测试时间信息是否填写完整
                    String s1 = during.getText().toString();
                    int t2 = s1.length();
                    int t = Integer.valueOf(s1.substring(0, t2 - 2));//检查持续时间是否填写
                    Log.i(TAG, "onClick: t:" + t);
                    int a = 0;//-1:date_srart<d;1:date_start>d
                    a = date_start.compareTo(d);
                    Log.i(TAG, "onClick: a:" + a);
                    if (a < 0) {
                        Toast.makeText(SetActivity.this, "预定的时间不能早于当前时间！", Toast.LENGTH_SHORT).show();
                    } else {
                        if (time * price == 0) {
                            Toast.makeText(SetActivity.this, "请选择座位", Toast.LENGTH_SHORT).show();
                        }
                        else if(checkTime(sdf.parse(s))){
                            Toast.makeText(SetActivity.this, "该时间段您已预订过座位！", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            setOrder.setOrder_time(sdf.format(d));
                            setOrder.setStart_time(sdf.format(date_start));
                            setOrder.setDuring_time(time);
                            setOrder.setCheck_time("");
                            setOrder.setMoney(time * price);
                            setOrder.setState(0);
                            setOrder.setContent((int) (Math.random() * 30) + 1);
                            Log.i(TAG, "onClick: setOrder:" + setOrder.show());
                            new AlertDialog.Builder(SetActivity.this)
                                    .setTitle("订单支付")
                                    .setMessage("是否支付" + time * price + "元")
                                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            DBManger_Set dbManger_set = new DBManger_Set(SetActivity.this);
                                            dbManger_set.add(setOrder);
                                            Toast.makeText(SetActivity.this, "支付成功！", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent();
                                            intent.setClass(SetActivity.this, MainActivity.class);
                                            intent.putExtra("id", 3);
                                            startActivity(intent);
                                        }
                                    })
                                    .setNegativeButton("取消", null).show();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(SetActivity.this, "请完整填写时间", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean checkTime(Date date) throws Exception {
        boolean ret = false;//默认false表示时间不冲突
        DBManger_Set dbManger_set = new DBManger_Set(SetActivity.this);
        ArrayList<HashMap<String, Object>> timeList=null;
        try {
            timeList = dbManger_set.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        if (timeList==null){
            Log.i(TAG, "checkTime: 座位订单数据为空");
        }
        else {
            for (int i = 0; i < timeList.size(); i++) {
                HashMap<String, Object> map = timeList.get(i);
                Date date0 = sdf.parse(map.get("start_time").toString());
                //计算结束时间
                int dd = Integer.valueOf(map.get("during_time").toString());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date0);
                calendar.add(Calendar.HOUR, dd);
                Date date1 = calendar.getTime();
                Log.i(TAG, "checkTime: ret:" + (date.after(date0) && date.before(date1)));
                //比较时间
                if (date.after(date0) && date.before(date1)) {
                    ret = true;
                    break;
                }
            }
        }
        return ret;
    }

}
