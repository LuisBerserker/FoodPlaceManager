package com.luis.foodplacemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Date;
import java.util.GregorianCalendar;

public class specifyItemAmount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specify_item_amount);
        Bundle extras = getIntent().getExtras();
        int DBposition = extras.getInt("EXTRA_CHOSEN_ITEM_POS");
        final BasicItem item = IOaquireableFoodsList.aquireableItemByDBPos(this, DBposition);
        TextView itemName = (TextView) findViewById(R.id.itemName);
        itemName.setText(item.getItemName());
        TextView itemDescription = (TextView)findViewById(R.id.itemDescription);
        itemDescription.setText(item.getItemDescription());
        final DatePicker expirationDatePicker = (DatePicker) findViewById(R.id.expirationDatePicker);
        final Switch switchExpires =(Switch)findViewById(R.id.switchExpires);
        Button deleteItemFromAQDB = (Button) findViewById(R.id.deleteItemFromAQDB);
        deleteItemFromAQDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IOaquireableFoodsList.deleteItem(getApplicationContext(), item.getItemName(),item.getItemDescription());
                finish();
            }
        });
        Button addToStorage =(Button) findViewById(R.id.addToStorage);
        addToStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IOavailableFoodsList.addItem(item.getId(), new Date(new GregorianCalendar(
                        expirationDatePicker.getYear(),
                        expirationDatePicker.getMonth(),
                        expirationDatePicker.getDayOfMonth()).getTimeInMillis()), switchExpires.isChecked(), getApplicationContext());
                finish();
            }

        });
    }
}
