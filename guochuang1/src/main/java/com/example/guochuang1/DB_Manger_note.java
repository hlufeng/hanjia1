package com.example.guochuang1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DB_Manger_note {
    private DBHelper dbHelper;
    private String TBNAME;

    public DB_Manger_note(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME4;
    }

    public void add(Note note) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TIME", note.getTime());
        values.put("CONTENT", note.getContent());
        db.insert(TBNAME, null, values);
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void update(Note note) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TIME", note.getTime());
        values.put("CONTENT", note.getContent());
        db.update(TBNAME, values, "ID=?", new String[]{String.valueOf(note.getId())});
        db.close();
    }


    public List<Note> listAll() {
        List<Note> itemList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        if (cursor != null) {
            itemList = new ArrayList<Note>();
            while (cursor.moveToNext()) {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                note.setTime(cursor.getString(cursor.getColumnIndex("TIME")));
                note.setContent(cursor.getString(cursor.getColumnIndex("CONTENT")));
                itemList.add(note);
            }
            cursor.close();
        }
        db.close();
        return itemList;
    }

    public Note findById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, "ID=?", new String[]{String.valueOf(id)}, null, null, null);
        Note note = null;
        if (cursor != null && cursor.moveToFirst()) {
            note = new Note();
            note.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            note.setTime(cursor.getString(cursor.getColumnIndex("TIME")));
            note.setContent(cursor.getString(cursor.getColumnIndex("CONTENT")));
            cursor.close();
        }
        db.close();
        return note;
    }
}
