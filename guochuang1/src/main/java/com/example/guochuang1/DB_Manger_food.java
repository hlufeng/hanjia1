package com.example.guochuang1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DB_Manger_food {
    private DBHelper dbHelper;
    private String TBNAME;

    public DB_Manger_food(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME3;
    }

    public void add(FoodOrder foodOrder) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ORDER_TIME", foodOrder.getOrder_time());
        values.put("MONEY", foodOrder.getMoney());
        values.put("CONTENT", foodOrder.getContent());
        db.insert(TBNAME, null, values);
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME, null, null);
        db.close();
    }

    public List<FoodOrder> listAll() {
        List<FoodOrder> itemList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        if (cursor != null) {
            itemList = new ArrayList<FoodOrder>();
            while (cursor.moveToNext()) {
                FoodOrder foodOrder = new FoodOrder();
                foodOrder.setOrder_time(cursor.getString(cursor.getColumnIndex("ORDER_TIME")));
                foodOrder.setMoney(cursor.getInt(cursor.getColumnIndex("MONEY")));
                foodOrder.setContent(cursor.getString(cursor.getColumnIndex("CONTENT")));
                itemList.add(foodOrder);
            }
            cursor.close();
        }
        db.close();
        return itemList;
    }

//    public SetOrder findById(String id) {
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.query(TBNAME, null, "ORDER_TIME=?", new String[]{id}, null, null, null);
//        SetOrder setOrder = null;
//        if (cursor != null && cursor.moveToFirst()) {
//            setOrder = new SetOrder();
//            setOrder.setOrder_time(cursor.getString(cursor.getColumnIndex("ORDER_TIME")));
//            setOrder.setStart_time(cursor.getString(cursor.getColumnIndex("START_TIME")));
//            setOrder.setDuring_time(cursor.getInt(cursor.getColumnIndex("DURING_TIME")));
//            setOrder.setCheck_time(cursor.getString(cursor.getColumnIndex("CHECK_TIME")));
//            setOrder.setMoney(cursor.getInt(cursor.getColumnIndex("MONEY")));
//            setOrder.setState(cursor.getInt(cursor.getColumnIndex("STATE")));
//            setOrder.setType(cursor.getString(cursor.getColumnIndex("TYPE")));
//            setOrder.setContent(cursor.getInt(cursor.getColumnIndex("CONTENT")));
//            cursor.close();
//        }
//        db.close();
//        return setOrder;
//    }

}
