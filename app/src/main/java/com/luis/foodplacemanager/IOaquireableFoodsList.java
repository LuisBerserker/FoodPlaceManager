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
     static ArrayList<String> words = new ArrayList<String>();
     static   boolean listReReadRequired = true;

    public static BasicItem[] ListAquireableFoods(Context context){
        DBHandler thisDBHandler = new DBHandler(context, null, null,1);
        return thisDBHandler.getItemList();

    }
    public static void addItem(String name, String description, Context context){
        DBHandler thisDBHandler = new DBHandler(context, null, null, 1);
        thisDBHandler.addItem(new BasicItem(name, description));
        return;
    }

}
