package com.example.guochuang1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManger_Set {
    private DBHelper dbHelper;
    private String TBNAME;

    public DBManger_Set(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME2;
    }

    public void add(SetOrder setOrder) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ORDER_TIME", setOrder.getOrder_time());
        values.put("START_TIME", setOrder.getStart_time());
        values.put("DURING_TIME", setOrder.getDuring_time());
        values.put("CHECK_TIME", setOrder.getCheck_time());
        values.put("MONEY", setOrder.getMoney());
        values.put("STATE", setOrder.getState());
        values.put("TYPE", setOrder.getType());
        values.put("CONTENT", setOrder.getContent());
        db.insert(TBNAME, null, values);
        db.close();
    }

    public void addAll(List<SetOrder> list) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (SetOrder setOrder : list) {
            ContentValues values = new ContentValues();
            values.put("ORDER_TIME", setOrder.getOrder_time());
            values.put("START_TIME", setOrder.getStart_time());
            values.put("DURING_TIME", setOrder.getDuring_time());
            values.put("CHECK_TIME", setOrder.getCheck_time());
            values.put("MONEY", setOrder.getMoney());
            values.put("STATE", setOrder.getState());
            values.put("TYPE", setOrder.getType());
            values.put("CONTENT", setOrder.getContent());
            db.insert(TBNAME, null, values);
        }
        db.close();
    }

    public void delete(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME, "ORDER_TIME=?", new String[]{id});
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME, null, null);
        db.close();
    }

    public void update(SetOrder setOrder) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("START_TIME", setOrder.getStart_time());
        values.put("DURING_TIME", setOrder.getDuring_time());
        values.put("CHECK_TIME", setOrder.getCheck_time());
        values.put("MONEY", setOrder.getMoney());
        values.put("STATE", setOrder.getState());
        values.put("TYPE", setOrder.getType());
        values.put("CONTENT", setOrder.getContent());
        db.update(TBNAME, values, "ORDER_TIME=?", new String[]{setOrder.getOrder_time()});
        db.close();
    }

    public List<SetOrder> listAll() {
        List<SetOrder> itemList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        if (cursor != null) {
            itemList = new ArrayList<SetOrder>();
            while (cursor.moveToNext()) {
                SetOrder setOrder = new SetOrder();
                setOrder.setOrder_time(cursor.getString(cursor.getColumnIndex("ORDER_TIME")));
                setOrder.setStart_time(cursor.getString(cursor.getColumnIndex("START_TIME")));
                setOrder.setDuring_time(cursor.getInt(cursor.getColumnIndex("DURING_TIME")));
                setOrder.setCheck_time(cursor.getString(cursor.getColumnIndex("CHECK_TIME")));
                setOrder.setMoney(cursor.getInt(cursor.getColumnIndex("MONEY")));
                setOrder.setState(cursor.getInt(cursor.getColumnIndex("STATE")));
                setOrder.setType(cursor.getString(cursor.getColumnIndex("TYPE")));
                setOrder.setContent(cursor.getInt(cursor.getColumnIndex("CONTENT")));
                itemList.add(setOrder);
            }
            cursor.close();
        }
        db.close();
        return itemList;
    }

    public SetOrder findById(String id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, "ORDER_TIME=?", new String[]{id}, null, null, null);
        SetOrder setOrder = null;
        if (cursor != null && cursor.moveToFirst()) {
            setOrder = new SetOrder();
            setOrder.setOrder_time(cursor.getString(cursor.getColumnIndex("ORDER_TIME")));
            setOrder.setStart_time(cursor.getString(cursor.getColumnIndex("START_TIME")));
            setOrder.setDuring_time(cursor.getInt(cursor.getColumnIndex("DURING_TIME")));
            setOrder.setCheck_time(cursor.getString(cursor.getColumnIndex("CHECK_TIME")));
            setOrder.setMoney(cursor.getInt(cursor.getColumnIndex("MONEY")));
            setOrder.setState(cursor.getInt(cursor.getColumnIndex("STATE")));
            setOrder.setType(cursor.getString(cursor.getColumnIndex("TYPE")));
            setOrder.setContent(cursor.getInt(cursor.getColumnIndex("CONTENT")));
            cursor.close();
        }
        db.close();
        return setOrder;
    }
}
