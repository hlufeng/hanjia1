package com.example.guochuang1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManger_Car {
    private DBHelper dbHelper;
    private String TBNAME;

    public DBManger_Car(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME;
    }

    public void add(Items items) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PIC", items.getPic());
        values.put("NAME", items.getName());
        values.put("PRICE", items.getPrice());
        values.put("NUM", items.getNum());
        db.insert(TBNAME, null, values);
        db.close();

    }

    public void addAll(List<Items> list) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (Items items : list) {
            ContentValues values = new ContentValues();
            values.put("PIC", items.getPic());
            values.put("NAME", items.getName());
            values.put("PRICE", items.getPrice());
            values.put("NUM", items.getNum());
            db.insert(TBNAME, null, values);
        }
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME, "PIC=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME, null, null);
        db.close();
    }

    public void update(Items items) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("pic", items.getPic());
        values.put("NAME", items.getName());
        values.put("PRICE", items.getPrice());
        values.put("NUM", items.getNum());
        db.update(TBNAME, values, "PIC=?", new String[]{String.valueOf(items.getPic())});
        db.close();
    }

    public List<Items> listAll() {
        List<Items> itemList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        if (cursor != null) {
            itemList = new ArrayList<Items>();
            while (cursor.moveToNext()) {
                Items item = new Items();
                item.setPic(cursor.getInt(cursor.getColumnIndex("PIC")));
                item.setName(cursor.getString(cursor.getColumnIndex("NAME")));
                item.setPrice(cursor.getInt(cursor.getColumnIndex("PRICE")));
                item.setNum(cursor.getInt(cursor.getColumnIndex("NUM")));
                itemList.add(item);
            }
            cursor.close();
        }
        db.close();
        return itemList;
    }

    public Items findById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, "PIC=?", new String[]{String.valueOf(id)}, null, null, null);
        Items items = null;
        if (cursor != null && cursor.moveToFirst()) {
            items = new Items();
            items.setPic(cursor.getInt(cursor.getColumnIndex("PIC")));
            items.setName(cursor.getString(cursor.getColumnIndex("NAME")));
            items.setPrice(cursor.getInt(cursor.getColumnIndex("PRICE")));
            items.setNum(cursor.getInt(cursor.getColumnIndex("NUM")));
            cursor.close();
        }
        db.close();
        return items;
    }

    public boolean ishere(int pic) {
        boolean a = false;
        List<Items> list = listAll();
        for (Items items : list) {
            if (items.getPic() == pic) {
                a = true;
                break;
            }
        }
        return a;
    }


}
