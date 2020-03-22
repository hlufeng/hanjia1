package com.example.guochuang1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends SimpleAdapter {
    private final View.OnClickListener listener;
    private ArrayList<HashMap<String, Object>> listItems;
    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     */
    public MyAdapter(View.OnClickListener listener,Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.listener=listener;
        listItems=(ArrayList<HashMap<String, Object>>)data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,false);
            holder.iv_del = (ImageView) convertView.findViewById(R.id.item_trc);
            holder.iv_plus = (ImageView) convertView.findViewById(R.id.item_plus);
            holder.num = (TextView) convertView.findViewById(R.id.item_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.num.setText(listItems.get(position).get("num").toString());

        //给要被点击的控件加入点击监听，具体事件在创建adapter的地方实现
        holder.iv_del.setOnClickListener(listener);
        holder.iv_plus.setOnClickListener(listener);
        //通过setTag 将被点击控件所在条目的位置传递出去
        holder.iv_del.setTag(position);
        holder.iv_plus.setTag(position);
        return super.getView(position, convertView, parent);

//        return convertView;
    }

    class ViewHolder {
        TextView  num;
        ImageView iv_del,iv_plus;
    }
}
