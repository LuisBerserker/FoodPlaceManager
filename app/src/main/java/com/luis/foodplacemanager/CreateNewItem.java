package com.luis.foodplacemanager;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateNewItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_item);

        Button button =(Button) findViewById(R.id.createButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ItemName = (EditText) findViewById(R.id.newItemName);
                EditText ItemDescription = (EditText) findViewById(R.id.newItemDescription);
                IOaquireableFoodsList.addItem(ItemName.getText().toString(), ItemDescription.getText().toString(), getApplicationContext());

                finish();
            }
        });
    }
}
