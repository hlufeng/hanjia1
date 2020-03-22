package com.example.guochuang1.ui.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.guochuang1.DBManger_Car;
import com.example.guochuang1.Items;
import com.example.guochuang1.MyAdapter;
import com.example.guochuang1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DashboardFragment extends Fragment implements View.OnClickListener {

    private DashboardViewModel dashboardViewModel;
    int total_price;
    List<Items> shoppingcar;
    ConstraintLayout constraintLayout;
    private ArrayList<HashMap<String, Object>> listItem;
    private SimpleAdapter listItemAdapter; // 适配器
    ListView listView;
    TextView bottom_tv2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        bottom_tv2 = root.findViewById(R.id.car_bottom_tv2);

        DBManger_Car dbManger_car = new DBManger_Car(getActivity());
        shoppingcar = dbManger_car.listAll();
        constraintLayout = root.findViewById(R.id.layout_car);
        if (shoppingcar.size() == 0) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(R.drawable.carnull);
            ConstraintLayout.LayoutParams param = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            param.height = 400;
            param.width = 400;
            param.bottomToBottom = R.id.layout_car;
            param.topToTop = R.id.layout_car;
            param.leftToLeft = R.id.layout_car;
            param.rightToRight = R.id.layout_car;
            imageView.setLayoutParams(param);
            constraintLayout.addView(imageView);
        } else {
            total_price = 0;
            listView = root.findViewById(R.id.car_list);
            listItem = new ArrayList<HashMap<String, Object>>();
            for (Items items : shoppingcar) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("pic", items.getPic());
                map.put("name", items.getName());
                map.put("price", "￥" + items.getPrice());
                map.put("num", items.getNum());
                total_price += items.getPrice() * items.getNum();
                listItem.add(map);
            }
            listItemAdapter = new MyAdapter(DashboardFragment.this, getActivity(), listItem, // listItems 数据源
                    R.layout.list_item, //ListItem 的 XML 布局实现
                    new String[]{"pic", "name", "price", "num"},
                    new int[]{R.id.item_pic, R.id.item_title, R.id.item_price, R.id.item_num});
            listView.setAdapter(listItemAdapter);
            bottom_tv2.setText("￥" + total_price);

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    new AlertDialog.Builder(getActivity())
                            .setMessage("是否删除该物品？")
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //按下确定键后的事件
                                    DBManger_Car dbManger_car1 = new DBManger_Car(getActivity());
                                    dbManger_car1.delete((int)listItem.get(position).get("pic"));
                                    total_price -= Integer.valueOf(listItem.get(position).get("price").toString().substring(1)) * (int) listItem.get(position).get("num");
                                    bottom_tv2.setText("￥" + total_price);
                                    listItem.remove(position);
                                    listItemAdapter.notifyDataSetChanged();
                                    if (listItem.size() == 0) {
                                        ImageView imageView = new ImageView(getActivity());
                                        imageView.setImageResource(R.drawable.carnull);
                                        ConstraintLayout.LayoutParams param = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                                        param.height = 400;
                                        param.width = 400;
                                        param.bottomToBottom = R.id.layout_car;
                                        param.topToTop = R.id.layout_car;
                                        param.leftToLeft = R.id.layout_car;
                                        param.rightToRight = R.id.layout_car;
                                        imageView.setLayoutParams(param);
                                        constraintLayout.addView(imageView);
                                    }
                                }
                            }).setNegativeButton("否",null).show();
                    return false;
                }
            });
        }
//设置自定义顶部导航栏文字
        TextView tv_title = getActivity().findViewById(R.id.tv_title);
        tv_title.setText("购物车");
        tv_title.setGravity(Gravity.CENTER);
        return root;
    }

    @Override
    public void onClick(View v) {
        final int position = (int) v.getTag(); //获取被点击的控件所在item 的位置，setTag 存储的object，所以此处要强转
        DBManger_Car dbManger_car = new DBManger_Car(getActivity());
        switch (v.getId()) {
            case R.id.item_trc:   //lv条目中 iv_del
                int a = (int) listItem.get(position).get("num") - 1;
                if (a > 0) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("pic", listItem.get(position).get("pic"));
                    map.put("name", listItem.get(position).get("name"));
                    map.put("price", listItem.get(position).get("price"));
                    map.put("num", a);
                    listItem.set(position, map);

                    Items items = new Items();
                    items.setPic((int) listItem.get(position).get("pic"));
                    items.setName((String) listItem.get(position).get("name"));
                    items.setPrice(Integer.valueOf(listItem.get(position).get("price").toString().substring(1)));
                    items.setNum(a);
                    dbManger_car.update(items);

                    //Log.i(TAG, "onClick: new_num:" + mainfood_num);
                    listItemAdapter.notifyDataSetChanged();
                    //更新总价
                    total_price -= Integer.valueOf(listItem.get(position).get("price").toString().substring(1));
                    bottom_tv2.setText("￥" + total_price);
                }
                break;
            case R.id.item_plus:
                int b = (int) listItem.get(position).get("num") + 1;
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("pic", listItem.get(position).get("pic"));
                map.put("name", listItem.get(position).get("name"));
                map.put("price", listItem.get(position).get("price"));
                map.put("num", b);
                listItem.set(position, map);

                Items items = new Items();
                items.setPic((int) listItem.get(position).get("pic"));
                items.setName((String) listItem.get(position).get("name"));
                items.setPrice(Integer.valueOf(listItem.get(position).get("price").toString().substring(1)));
                items.setNum(b);
                dbManger_car.update(items);

                //Log.i(TAG, "onClick: new_num:" + mainfood_num);
                listItemAdapter.notifyDataSetChanged();
                //更新总价
                total_price += Integer.valueOf(listItem.get(position).get("price").toString().substring(1));
                bottom_tv2.setText("￥" + total_price);
                break;
        }
    }
}
