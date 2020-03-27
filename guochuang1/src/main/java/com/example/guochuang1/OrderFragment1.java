package com.example.guochuang1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class OrderFragment1 extends Fragment {
    private final String TAG = "OrderFragment1";
    private ArrayList<HashMap<String,Object>> listItem;
    private SimpleAdapter listItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_order1, container, false);
        ListView listView = root.findViewById(R.id.orderlist1);
        DBManger_Set dbManger_set = new DBManger_Set(getActivity());
        List<SetOrder> orders=dbManger_set.listAll();
        listItem = new ArrayList<HashMap<String, Object>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        for (SetOrder setOrder : orders) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            String content=setOrder.getStart_time()+"开始"+setOrder.getDuring_time()+"小时的独处\n座位编号："+setOrder.getType()+setOrder.getContent();
            map.put("order_time", "订单时间："+setOrder.getOrder_time());
            map.put("content", content);
            //状态的确定
            String state="";
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(sdf.parse(setOrder.getStart_time()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.add(Calendar.HOUR, setOrder.getDuring_time());
            Date date1 = calendar.getTime();
            Date date = new Date();
            Date date0=null;
            try {
                date0 = sdf.parse(setOrder.getStart_time());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(date1.before(date)){
                state="已失效";
            }else {
                try {
                    if(date0.after(date)){
                        state = "未到期";
                    }else {
                        state="生效中";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            map.put("state", "订单状态："+state);
            map.put("money", "金额："+setOrder.getMoney()+"元");
            listItem.add(map);
        }
        listItemAdapter=new SimpleAdapter(getActivity(),listItem,R.layout.set_order_item,
                new String[]{"order_time", "content", "state", "money"},
                new int[]{R.id.item_order_time, R.id.item_content, R.id.item_state, R.id.item_money});
        listView.setAdapter(listItemAdapter);
        return root;
    }

}
