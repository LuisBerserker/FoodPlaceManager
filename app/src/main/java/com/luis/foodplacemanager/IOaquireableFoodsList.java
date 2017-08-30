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

    public static String[] ListAquireableFoods(Context context) {
        if (listReReadRequired) {
            try {
                words.clear();
                AssetManager mngr = context.getAssets();
                InputStream stream = context.getResources().getAssets().open("aquireableFoods.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String line;
                while ((line = reader.readLine()) != null) {
                    words.add(line);
                }
                reader.close();
                listReReadRequired = false;
                return words.toArray(new String[0]);
            }
            catch (IOException ex) {
                return new String[]{"This","shouldn't","be","here", "Exception:", ex.toString()};
            }

        }
        return words.toArray(new String[0]);
    }
    public static String[] testList(Context context){
        return new String[]{"Potato","government"};
    }
    public static String[] ListA(Context context){
        DBHandler thisDBHandler = new DBHandler(context, null, null,1);
        thisDBHandler.addItem(new BasicItem("potato","if it came into government it would be horrible"));
        BasicItem[] itemArray= thisDBHandler.getItemList();
        String[] output = new String[itemArray.length];
        for (int i = 0; i<output.length; i++){
            output[i]=itemArray[i].getItemName();
        }
        return output;
    }

}
