package com.example.guochuang1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment2 extends Fragment {

    private final String TAG = "OrderFragment2";
    private ArrayList<HashMap<String,Object>> listItem;
    private SimpleAdapter listItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_order2, container, false);
        ListView listView = root.findViewById(R.id.orderlist2);
        DB_Manger_food db_manger_food = new DB_Manger_food(getActivity());
        List<FoodOrder> orders=db_manger_food.listAll();
        listItem = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        for (FoodOrder foodOrder : orders) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("order_time", "订单时间："+foodOrder.getOrder_time());
            map.put("content", foodOrder.getContent());
            map.put("money", "金额："+foodOrder.getMoney()+"元");
            listItem.add(map);
        }
        listItemAdapter=new SimpleAdapter(getActivity(),listItem,R.layout.food_order_item,
                new String[]{"order_time", "content", "money"},
                new int[]{R.id.food_time, R.id.food_content, R.id.food_money});
        listView.setAdapter(listItemAdapter);

        return root;
    }
}
