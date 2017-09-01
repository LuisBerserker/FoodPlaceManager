package com.luis.foodplacemanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Date;

public class MainStorageDisplay extends AppCompatActivity {
    public static IOaquireableFoodsList iOaquireableFoodsList;
    public static IOavailableFoodsList iOavailableFoodsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iOaquireableFoodsList = new IOaquireableFoodsList();
        iOavailableFoodsList=new IOavailableFoodsList();
        StorageItem[] availableFood= IOavailableFoodsList.ListAvailableFoods(this);
        setContentView(R.layout.activity_main_storage_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("main menu", "test");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addItem);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainStorageDisplay.this, ChoseNewItem.class);
                startActivity(intent);
            }
        });
        ArrayAdapter adapt = new ArrayAdapter<String>(this, R.layout.activity_listview, getNameArray(availableFood));
        ListView listView = (ListView)findViewById(R.id.listOfavailableFoods);
        listView.setAdapter(adapt);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(MainStorageDisplay.this, ChoseNewItem.class);
//                startActivity(intent);
//            }
//        });
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_storage_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String[] getNameArray(StorageItem[] availableFoods){
        String[] output = new String[availableFoods.length];
        for(int i=0; i<output.length;i++){
            output[i]=IOaquireableFoodsList.findItemByID(getApplicationContext(), availableFoods[i].getItemID()).getItemName()+"   "+ availableFoods[i].getExpirationDate();
        }
        return output;
    }
}
