package com.luis.foodplacemanager;

/**
 * Created by Luis on 17.08.2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME ="foodPlaceManagerDB.db";
    private static final String TABLE_ACQUIRABLE = "aquireableFoods";
    private static final String TABLE_AVAILABLE = "availableFoods";

    //aq
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ITEMNAME = "itemname";
    public static final String COLUMN_ITEMDESCRIPTION="itemdescription";

    //av
    //public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ITEMID = "itemid";
    public static final String COLUMN_EXPIRATIONDATE= "expireDate";
    public static final String COLUMN_EXPIREABLE="expireable";

    private Context context;

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEMS_TABLE="CREATE TABLE "+
                TABLE_ACQUIRABLE +"("+
                COLUMN_ID+ " INTEGER PRIMARY KEY,"+
                COLUMN_ITEMNAME+" TEXT,"+
                COLUMN_ITEMDESCRIPTION +" TEXT"+
                ")";
        db.execSQL(CREATE_ITEMS_TABLE);
        String CREATE_STORAGE_TABLE = "CREATE TABLE "+
                TABLE_AVAILABLE +"("+
                COLUMN_ID+ " INTEGER PRIMARY KEY,"+
                COLUMN_ITEMID+" INTEGER,"+
                COLUMN_EXPIRATIONDATE+ " INTEGER,"+
                COLUMN_EXPIREABLE+" INTEGER)";
        db.execSQL(CREATE_STORAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
    }
    public void addItem(BasicItem item){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEMNAME, item.getItemName());
        values.put(COLUMN_ITEMDESCRIPTION, item.getItemDescription());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_ACQUIRABLE, null, values);
        db.close();
    }
    public void deleteItem(String NAME, String DESCRIPTION){
        String DELETE_ITEM = "DELETE FROM "+
                TABLE_ACQUIRABLE+ " WHERE "+
                COLUMN_ITEMNAME + "= '"+
                 NAME+ "' AND "+
                COLUMN_ITEMDESCRIPTION+ "= '"+
                DESCRIPTION+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DELETE_ITEM);
        db.close();
    }
    public BasicItem findItemByID(int ID){
        String query ="SELECT * FROM "+ TABLE_ACQUIRABLE+
                " WHERE "+ COLUMN_ID+"= '"+
                ID+"'";
        BasicItem output = new BasicItem();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
        }
        output.setID(cursor.getInt(0));
        output.setItemName(cursor.getString(1));
        output.set_itemdescription(cursor.getString(2));
        return output;
    }
    public BasicItem[] getItemList(){
        String query = "SELECT * FROM "+ TABLE_ACQUIRABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<BasicItem> output = new ArrayList<BasicItem>();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
        for (int i =0; i<cursor.getCount(); i++){
            output.add(new BasicItem(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2)
            ));
            cursor.moveToNext();
        }}
        cursor.close();
        db.close();
        return   output.toArray(new BasicItem[0]);
    }
    public void addStorage(int ItemID, Date expirationDate, boolean expires ){
        ContentValues values = new ContentValues();

        values.put(COLUMN_ITEMID, ItemID);
        values.put(COLUMN_EXPIRATIONDATE, expirationDate.getTime());
        int i =0;
        if (expires){i++;}
        values.put(COLUMN_EXPIREABLE,i );

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_AVAILABLE, null, values);
        db.close();
    }
    public void deleteStorage(int ItemID){
        String DELETE_STORAGE = "DELETE FROM "+
                TABLE_AVAILABLE+" WHERE "+
                COLUMN_ITEMID+"= '"+
                ItemID+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DELETE_STORAGE);
        db.close();
    }
    public StorageItem[] readStorage(){
        String query = "SELECT * FROM "+ TABLE_AVAILABLE+" ORDER BY "+ COLUMN_EXPIRATIONDATE+" DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<StorageItem> output = new ArrayList<StorageItem>();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            for (int i =0; i<cursor.getCount(); i++){
                output.add(new StorageItem(
                        Integer.parseInt(cursor.getString(1)),
                        new Date(Long.parseLong(cursor.getString(2))),
                        Integer.parseInt(cursor.getString(3))
                ));
                cursor.moveToNext();
            }}
        cursor.close();
        db.close();
        return   output.toArray(new StorageItem[0]);
    }
}
