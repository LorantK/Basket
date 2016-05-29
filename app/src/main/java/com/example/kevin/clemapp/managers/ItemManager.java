package com.example.kevin.clemapp.managers;

/**
 * Created by Kevin on 29/05/2016.
 * D'après http://fr.jeffprod.com/blog/2015/utilisation-d-une-base-sqlite-sous-android.html
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.kevin.clemapp.models.Item;
import com.example.kevin.clemapp.tools.MySQLite;

public class ItemManager {

    private static final String TABLE_NAME = "item";
    public static final String KEY_ID_ITEM="id";
    public static final String KEY_NOM_ITEM="name";
    public static final String KEY_PRICE_ITEM="price";
    public static final String KEY_SELLER_ITEM="seller";
    public static final String KEY_URL_ITEM="url";
    public static final String CREATE_TABLE_ITEM = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+KEY_ID_ITEM+" INTEGER primary key," +
            " "+KEY_NOM_ITEM+" TEXT" +
            " "+KEY_PRICE_ITEM+" TEXT" +
            " "+KEY_SELLER_ITEM+" TEXT" +
            " "+KEY_URL_ITEM+" TEXT" +
            ");";
    private MySQLite maBaseSQLite;
    private SQLiteDatabase db;


    public ItemManager(Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open()
    {
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        db.close();
    }

    public long addItem(Item item) {

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_ITEM, item.getName());

        return db.insert(TABLE_NAME,null,values);
    }

    public int modItem(Item item) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_ITEM, item.getName());
        values.put(KEY_PRICE_ITEM, item.getPrice());
        values.put(KEY_SELLER_ITEM, item.getSeller());
        values.put(KEY_URL_ITEM, item.getUrl());

        String where = KEY_ID_ITEM+" = ?";
        String[] whereArgs = {item.getId()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supItem(Item item) {

        String where = KEY_ID_ITEM+" = ?";
        String[] whereArgs = {item.getId()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Item getItem(int id) {

        Item i=new Item(0,"","","","");

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_ITEM+"="+id, null);
        if (c.moveToFirst()) {
            i.setId(c.getInt(c.getColumnIndex(KEY_ID_ITEM)));
            i.setName(c.getString(c.getColumnIndex(KEY_NOM_ITEM)));
            i.setPrice(c.getString(c.getColumnIndex(KEY_PRICE_ITEM)));
            i.setSeller(c.getString(c.getColumnIndex(KEY_SELLER_ITEM)));
            i.setUrl(c.getString(c.getColumnIndex(KEY_URL_ITEM)));
            c.close();
        }

        return i;
    }

    public Cursor getItems() {

        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

}

