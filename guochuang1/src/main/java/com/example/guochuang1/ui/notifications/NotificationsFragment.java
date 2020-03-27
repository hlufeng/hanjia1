package com.example.guochuang1.ui.notifications;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.guochuang1.DBManger_Set;
import com.example.guochuang1.MyManager;
import com.example.guochuang1.NoteListActivity;
import com.example.guochuang1.OrderListActivity;
import com.example.guochuang1.R;
import com.example.guochuang1.SetOrder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private final String TAG = "man_center";
    private NotificationsViewModel notificationsViewModel;
    TextView tv1, tv2, tv3, state_tv, check_tv, time;
    int alone_time;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //
            }
        });
        //设置自定义顶部导航栏文字
        TextView tv_title = getActivity().findViewById(R.id.tv_title);
        tv_title.setText("个人中心");
        tv_title.setGravity(Gravity.CENTER);
        tv1 = root.findViewById(R.id.tv1);
        tv2 = root.findViewById(R.id.tv2);
        tv3 = root.findViewById(R.id.tv3);
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"暂未开放",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getActivity(), OrderListActivity.class);
                startActivity(intent);
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "暂未开放", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getActivity(), NoteListActivity.class);
                startActivity(intent);
            }
        });
        tv3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final EditText et = new EditText(getActivity());
                et.setInputType(InputType.TYPE_CLASS_NUMBER);
                new AlertDialog.Builder(getActivity()).setTitle("请输入密钥")
                        .setView(et)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //按下确定键后的事件
                                if (et.getText().toString().equals("666666")) {
                                    Intent intent = new Intent();
                                    intent.setClass(getActivity(), MyManager.class);
                                    getActivity().startActivity(intent);
                                } else {
                                    Toast.makeText(getActivity(), "密码错误！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("取消", null).show();
                return false;
            }
        });
        //座位状态
        state_tv = root.findViewById(R.id.set_state);
        check_tv = root.findViewById(R.id.checkview);
        String str = "";
        try {
            final SetOrder setOrder = getbestItem();
            if (setOrder.getStart_time().equals("")) {
                Log.i(TAG, "onCreateView: 没有未过期或生效中的座位");
                check_tv.setVisibility(View.GONE);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
                if (new Date().after(sdf.parse(setOrder.getStart_time()))) {
                    //生效中
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(sdf.parse(setOrder.getStart_time()));
                    calendar.add(Calendar.HOUR, setOrder.getDuring_time());
                    final Date date = calendar.getTime();
                    long a = date.getTime() - (new Date().getTime());
                    int t = (int) a / 60000;
                    str = "座位使用中\n座位编号：" + setOrder.getType() + setOrder.getContent() + "\n距离结束时间还有" + t + "分钟";
                    if (setOrder.getCheck_time().equals("")) {
                        //未打卡
                        check_tv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                check_tv.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "打卡成功", Toast.LENGTH_SHORT).show();
                                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
                                setOrder.setCheck_time(sdf1.format(new Date()));
                                DBManger_Set dbManger_set = new DBManger_Set(getActivity());
                                dbManger_set.update(setOrder);
                                check_tv.setText("已打卡");
                            }
                        });
                    } else {
                        Log.i(TAG, "onCreateView: 已打过卡");
                        check_tv.setText("已打卡");
                    }
                } else {
                    //未生效
                    str = "座位锁定中\n将于" + setOrder.getStart_time() + "生效\n座位编号：" + setOrder.getType() + setOrder.getContent();
                    check_tv.setVisibility(View.GONE);
                }
                state_tv.setText(str);
            }
        } catch (Exception e) {
            check_tv.setVisibility(View.GONE);
        }
        //独处时间,单独考虑生效中的座位
        DBManger_Set dbManger_set1 = new DBManger_Set(getActivity());
        time = root.findViewById(R.id.time);
        int fz = 0;
        List<SetOrder> list = dbManger_set1.listAll();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        long hm = 0;
        for (SetOrder setOrder : list) {
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(sdf2.parse(setOrder.getStart_time()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.add(Calendar.HOUR, setOrder.getDuring_time());
            Date date1 = calendar.getTime();
            Date date = new Date();
            Date date0 = null;
            try {
                date0 = sdf2.parse(setOrder.getStart_time());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date.after(date0) && date.before(date1)) {
                //生效中
                try {
                    long k = date.getTime() - sdf2.parse(setOrder.getCheck_time()).getTime();
                    hm += k;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    long k = date1.getTime() - sdf2.parse(setOrder.getCheck_time()).getTime();
                    hm += k;
                } catch (Exception e) {
                    hm += 0;
                }
            }
        }
        fz = (int) hm / 60000;
        Log.i(TAG, "onCreateView: hm:" + hm);
        time.setText(fz + "分钟");

        return root;
    }

    //选取end_time>当前时间，并且距离end_time最近的时间
    public SetOrder getbestItem() throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        DBManger_Set dbManger_set = new DBManger_Set(getActivity());
        List<SetOrder> orders = dbManger_set.listAll();
        if (orders.size() == 0) {
            return new SetOrder();
        } else {
            long b = 2056467365;
            String c = "";
            for (SetOrder order : orders) {
                Date date0 = sdf.parse(order.getStart_time());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date0);
                calendar.add(Calendar.HOUR, order.getDuring_time());
                Date date1 = calendar.getTime();
                if (date1.after(date) && (date1.getTime() - date.getTime()) < b) {
                    Log.i(TAG, "getbestItem: t:" + (date1.getTime() - date.getTime()));
                    b = date1.getTime() - date.getTime();
                    c = order.getOrder_time();
                }
            }
            SetOrder setOrder = dbManger_set.findById(c);
            return setOrder;
        }
    }
}
