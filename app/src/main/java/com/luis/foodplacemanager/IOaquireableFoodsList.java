package com.luis.foodplacemanager;

import android.content.ClipData;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLInput;
import java.util.ArrayList;

/**
 * Created by Luis on 08.08.2017.
 */

public class IOaquireableFoodsList {
    public static BasicItem[] ListAquireableFoods(Context context){
        DBHandler thisDBHandler = new DBHandler(context, null, null,1);
        return thisDBHandler.getItemList();
    }
    public static void addItem(String name, String description, Context context){
        DBHandler thisDBHandler = new DBHandler(context, null, null, 1);
        thisDBHandler.addItem(new BasicItem(name, description));
        return;
    }
    public static BasicItem aquireableItemByDBPos(Context context, int i){
        DBHandler dbHandler=new DBHandler(context, null,null,1);
        return dbHandler.getItemList()[i];
    }
    public static void deleteItem(Context context, String name, String description){
        DBHandler dbHandler=new DBHandler(context, null,null,1);
        dbHandler.deleteItem(name, description);
    }

    public static BasicItem findItemByID(Context context, int ID){
        DBHandler dbHandler=new DBHandler(context, null,null,1);
        return dbHandler.findItemByID(ID);
    }
}
