package com.luis.foodplacemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;

public class ChoseNewItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_new_item);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, IOaquireableFoodsList.ListA(this));
        ListView listView = (ListView)findViewById(R.id.listOfaquireableFoods);
        listView.setAdapter(adapter);


    }
}
