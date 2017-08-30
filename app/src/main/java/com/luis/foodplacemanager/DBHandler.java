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

import java.util.ArrayList;


public class DBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME ="foodPlaceManagerDB.db";
    private static final String TABLE_ACQUIRABLE = "aquireableFoods";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ITEMNAME = "itemname";
    public static final String COLUMN_ITEMDESCRIPTION="itemdescription";
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
        Log.d("create table: ",CREATE_ITEMS_TABLE );
        db.execSQL(CREATE_ITEMS_TABLE);
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

}
