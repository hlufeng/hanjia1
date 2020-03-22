package com.example.guochuang1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FoodListActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "FoodListActivity";
    int total_price;
    TextView title, bottom_tv2;
    String site;
    ListView listView;
    Button check;
    ImageButton back, car;
    List mainfood_pic, cafe_pic, icream_pic, cold_pic, tea_pic, sweat_pic;
    List mainfood_name, cafe_name, icream_name, cold_name, tea_name, sweat_name;
    List mainfood_price, cafe_price, icream_price, cold_price, tea_price, sweat_price;
    List mainfood_num, cafe_num, icream_num, cold_num, tea_num, sweat_num;
    private ArrayList<HashMap<String, Object>> listItem, listItems_mainfood, listItems_cafe, listItems_icream, listItems_cold, listItems_tea, listItems_sweat; // 存放文字、图片信息
    private SimpleAdapter listItemAdapter; // 适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        bottom_tv2 = findViewById(R.id.car_bottom_tv2);
        //仅去掉标题栏，系统状态栏还是会显示
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        title = findViewById(R.id.tv_title2);
        final Intent intent = getIntent();
        site = intent.getStringExtra("type");
        title.setText(site);
        title.setGravity(Gravity.CENTER);
        back = findViewById(R.id.ib_title_back1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodListActivity.this.finish();
            }
        });
        car = findViewById(R.id.bar_car);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(FoodListActivity.this, MainActivity.class);
                intent1.putExtra("id", 2);
                startActivity(intent1);
            }
        });

        initdata();

        listView = findViewById(R.id.food_list);
        if ("主食".equals(site)) {
            listItem = listItems_mainfood;
        } else if ("咖啡".equals(site)) {
            listItem = listItems_cafe;
        } else if ("冰淇淋".equals(site)) {
            listItem = listItems_icream;
        } else if ("冰饮".equals(site)) {
            listItem = listItems_cold;
        } else if ("茶饮".equals(site)) {
            listItem = listItems_tea;
        } else if ("甜点".equals(site)) {
            listItem = listItems_sweat;
        }
        listItemAdapter = new MyAdapter(FoodListActivity.this, this, listItem, // listItems 数据源
                R.layout.list_item, //ListItem 的 XML 布局实现
                new String[]{"pic", "name", "price", "num"},
                new int[]{R.id.item_pic, R.id.item_title, R.id.item_price, R.id.item_num});
        listView.setAdapter(listItemAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(FoodListActivity.this, position + "号位置的条目被点击", Toast.LENGTH_SHORT).show();
            }
        });
        Log.i(TAG, "onCreate: text");
        //加入购物车
        check = findViewById(R.id.car_bottom_check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (total_price != 0) {
                    List<Items> list = new ArrayList<Items>();
                    for (HashMap<String, Object> map : listItem) {
                        if ((int) map.get("num") != 0) {
                            Items items = new Items();
                            items.setPic((int) map.get("pic"));
                            items.setName((String) map.get("name"));
                            items.setPrice(Integer.valueOf(map.get("price").toString().substring(1)));
                            items.setNum((int) map.get("num"));
                            list.add(items);
                            map.put("num", 0);
                        }
                    }
                    total_price = 0;
                    bottom_tv2.setText("0");
                    listItemAdapter.notifyDataSetChanged();
                    Toast.makeText(FoodListActivity.this, "加入购物车成功", Toast.LENGTH_SHORT).show();
                    //向购物车传参
                    DBManger_Car dbManger_car = new DBManger_Car(FoodListActivity.this);
                    for (Items items : list) {
                        boolean a = dbManger_car.ishere(items.getPic());
                        if (a) {
                            Items old_items = dbManger_car.findById(items.getPic());
                            int new_num=old_items.getNum()+items.getNum();
                            items.setNum(new_num);
                            dbManger_car.update(items);
                        } else {
                            dbManger_car.add(items);
                        }
                    }
                }
            }
        });
    }

    //初始化数据
    public void initdata() {
        total_price = 0;
        //初始化主食数据
        mainfood_pic = new ArrayList();
        mainfood_name = new ArrayList();
        mainfood_price = new ArrayList();
        mainfood_num = new ArrayList();
        mainfood_pic.add(R.drawable.mainfood1);
        mainfood_pic.add(R.drawable.mainfood2);
        mainfood_pic.add(R.drawable.mainfood3);
        mainfood_pic.add(R.drawable.mainfood4);
        mainfood_pic.add(R.drawable.mainfood5);
        mainfood_pic.add(R.drawable.mainfood6);
        mainfood_pic.add(R.drawable.mainfood7);
        mainfood_pic.add(R.drawable.mainfood8);
        mainfood_pic.add(R.drawable.mainfood9);
        mainfood_pic.add(R.drawable.mainfood10);
        mainfood_pic.add(R.drawable.mainfood11);

        mainfood_name.add("扬州炒饭");
        mainfood_name.add("猪肉饺子");
        mainfood_name.add("抄手");
        mainfood_name.add("手抓饼");
        mainfood_name.add("牛肉面");
        mainfood_name.add("卤肉面");
        mainfood_name.add("招牌炒面");
        mainfood_name.add("杂酱面");
        mainfood_name.add("白米饭");
        mainfood_name.add("番茄炒蛋");
        mainfood_name.add("卤肉饭");

        mainfood_price.add(11);
        mainfood_price.add(12);
        mainfood_price.add(12);
        mainfood_price.add(10);
        mainfood_price.add(12);
        mainfood_price.add(12);
        mainfood_price.add(15);
        mainfood_price.add(13);
        mainfood_price.add(2);
        mainfood_price.add(9);
        mainfood_price.add(18);

        for (int i = 1; i <= mainfood_pic.size(); i++) {
            mainfood_num.add(0);
        }

        //初始化咖啡数据
        cafe_pic = new ArrayList();
        cafe_name = new ArrayList();
        cafe_price = new ArrayList();
        cafe_num = new ArrayList();
        cafe_pic.add(R.drawable.cafe1);
        cafe_pic.add(R.drawable.cafe2);
        cafe_pic.add(R.drawable.cafe3);

        cafe_name.add("猫屎咖啡");
        cafe_name.add("纯咖啡");
        cafe_name.add("卡布奇诺");

        cafe_price.add(23);
        cafe_price.add(18);
        cafe_price.add(22);

        for (int i = 1; i <= cafe_pic.size(); i++) {
            cafe_num.add(0);
        }

        //初始化冰淇淋数据
        icream_pic = new ArrayList();
        icream_name = new ArrayList();
        icream_price = new ArrayList();
        icream_num = new ArrayList();
        icream_pic.add(R.drawable.icream1);
        icream_pic.add(R.drawable.icream2);
        icream_pic.add(R.drawable.icream3);
        icream_pic.add(R.drawable.icream4);

        icream_name.add("甜筒");
        icream_name.add("巧克力圣代");
        icream_name.add("冰淇淋球");
        icream_name.add("水果冰淇淋");

        icream_price.add(5);
        icream_price.add(20);
        icream_price.add(10);
        icream_price.add(22);

        for (int i = 1; i <= icream_pic.size(); i++) {
            icream_num.add(0);
        }

        //初始化冰饮数据
        cold_pic = new ArrayList();
        cold_name = new ArrayList();
        cold_price = new ArrayList();
        cold_num = new ArrayList();
        cold_pic.add(R.drawable.cold1);
        cold_pic.add(R.drawable.cold2);

        cold_name.add("冰镇奶茶");
        cold_name.add("鲜榨果汁");

        cold_price.add(10);
        cold_price.add(12);

        for (int i = 1; i <= cold_pic.size(); i++) {
            cold_num.add(0);
        }

        //初始化茶饮数据
        tea_pic = new ArrayList();
        tea_name = new ArrayList();
        tea_price = new ArrayList();
        tea_num = new ArrayList();
        tea_pic.add(R.drawable.tea1);
        tea_pic.add(R.drawable.tea2);
        tea_pic.add(R.drawable.tea3);

        tea_name.add("芋圆奶茶");
        tea_name.add("奥利奥奶茶");
        tea_name.add("波霸奶茶");

        tea_price.add(15);
        tea_price.add(18);
        tea_price.add(13);

        for (int i = 1; i <= tea_pic.size(); i++) {
            tea_num.add(0);
        }
        //初始化甜品数据
        sweat_pic = new ArrayList();
        sweat_name = new ArrayList();
        sweat_price = new ArrayList();
        sweat_num = new ArrayList();
        sweat_pic.add(R.drawable.sweat1);
        sweat_pic.add(R.drawable.sweat2);
        sweat_pic.add(R.drawable.sweat3);
        sweat_pic.add(R.drawable.sweat4);
        sweat_pic.add(R.drawable.sweat5);

        sweat_name.add("奶油蛋糕");
        sweat_name.add("樱桃慕斯");
        sweat_name.add("草莓酥");
        sweat_name.add("巧克力薄饼");
        sweat_name.add("燕麦酥");

        sweat_price.add(18);
        sweat_price.add(15);
        sweat_price.add(10);
        sweat_price.add(15);
        sweat_price.add(10);

        for (int i = 1; i <= sweat_pic.size(); i++) {
            sweat_num.add(0);
        }

//初始化数组
        listItems_mainfood = initItem(mainfood_pic, mainfood_name, mainfood_price, mainfood_num);
        listItems_cafe = initItem(cafe_pic, cafe_name, cafe_price, cafe_num);
        listItems_icream = initItem(icream_pic, icream_name, icream_price, icream_num);
        listItems_cold = initItem(cold_pic, cold_name, cold_price, cold_num);
        listItems_tea = initItem(tea_pic, tea_name, tea_price, tea_num);
        listItems_sweat = initItem(sweat_pic, sweat_name, sweat_price, sweat_num);
    }


    @Override
    public void onClick(View v) {
        final int position = (int) v.getTag(); //获取被点击的控件所在item 的位置，setTag 存储的object，所以此处要强转
        switch (v.getId()) {
            case R.id.item_trc:   //lv条目中 iv_del
                int a = (int) listItem.get(position).get("num") - 1;
                if (a >= 0) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("pic", listItem.get(position).get("pic"));
                    map.put("name", listItem.get(position).get("name"));
                    map.put("price", listItem.get(position).get("price"));
                    map.put("num", a);
                    listItem.set(position, map);
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
                //Log.i(TAG, "onClick: new_num:" + mainfood_num);
                listItemAdapter.notifyDataSetChanged();
                //更新总价
                total_price += Integer.valueOf(listItem.get(position).get("price").toString().substring(1));
                bottom_tv2.setText("￥" + total_price);
                break;
        }
    }

    //初始化数组
    public ArrayList<HashMap<String, Object>> initItem(List pic, List name, List price, List num) {
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < pic.size(); i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("pic", pic.get(i));
            map.put("name", name.get(i));
            map.put("price", "￥" + price.get(i));
            map.put("num", num.get(i));
            arrayList.add(map);
        }
        return arrayList;
    }
}
