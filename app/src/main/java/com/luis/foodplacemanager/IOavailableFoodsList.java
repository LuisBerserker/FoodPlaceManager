package com.luis.foodplacemanager;

import android.content.Context;

import java.util.Date;

/**
 * Created by Luis on 01.09.2017.
 */

public class IOavailableFoodsList {
    public static void deleteItem(Context context, int ItemID, String description){
        DBHandler dbHandler=new DBHandler(context, null,null,1);
        dbHandler.deleteStorage(ItemID);

    }
    public static StorageItem[] ListAvailableFoods(Context context){
        DBHandler thisDBHandler = new DBHandler(context, null, null,1);
        return thisDBHandler.readStorage();
    }
    public static void addItem(int ItemID, Date date,boolean expires, Context context){
        DBHandler thisDBHandler = new DBHandler(context, null, null, 1);
        thisDBHandler.addStorage(ItemID, date, expires);
        return;
    }
}
