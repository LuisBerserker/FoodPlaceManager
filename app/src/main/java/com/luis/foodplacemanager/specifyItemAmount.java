package com.luis.foodplacemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class specifyItemAmount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specify_item_amount);
        Bundle extras = getIntent().getExtras();
        int DBposition = extras.getInt("EXTRA_CHOSEN_ITEM_POS");
        final BasicItem item = IOaquireableFoodsList.aquireableItemByDBPos(this, DBposition);
        TextView testText = (TextView) findViewById(R.id.testText);
        testText.setText(item.getItemName());
        Button deleteItemFromAQDB = (Button) findViewById(R.id.deleteItemFromAQDB);
        deleteItemFromAQDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IOaquireableFoodsList.deleteItem(getApplicationContext(), item.getItemName(),item.getItemDescription());
                finish();
            }
        });
    }
}
