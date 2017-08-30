package com.luis.foodplacemanager;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;

public class ChoseNewItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_new_item);
        BasicItem[] aquireableFoods = IOaquireableFoodsList.ListAquireableFoods(this);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, getNameArray(aquireableFoods));
        ListView listView = (ListView)findViewById(R.id.listOfaquireableFoods);
        listView.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.createItem);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoseNewItem.this, CreateNewItem.class);
                startActivity(intent);
            }
        });

    }
    private String[] getNameArray(BasicItem[] aquireableFoods){
        String[] output = new String[aquireableFoods.length];
        for(int i=0; i<output.length;i++){
            output[i]=aquireableFoods[i].getItemName();
        }
        return output;
    }
}
