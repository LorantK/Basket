package com.example.kevin.clemapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kevin.clemapp.R;
import com.example.kevin.clemapp.managers.ItemManager;
import com.example.kevin.clemapp.models.Item;
import com.example.kevin.clemapp.parsing.Parser;

import org.w3c.dom.Text;

public class ItemEditActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        TextView nametv = (TextView) findViewById(R.id.name);
        nametv.setText(getIntent().getStringExtra("name"));
        TextView sellertv = (TextView) findViewById(R.id.seller);
        sellertv.setText(getIntent().getStringExtra("seller"));
        TextView pricetv = (TextView) findViewById(R.id.price);
        pricetv.setText(getIntent().getStringExtra("price"));
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.content);
    }


    public void onClick(View v) {
        ItemManager im = new ItemManager(this);
        im.open();
        im.addItem(new Item(0,
                getIntent().getStringExtra("name"),
                getIntent().getStringExtra("price"),
                getIntent().getStringExtra("seller"),
                ""));

        Cursor c = im.getItems();
        if (c.moveToFirst()) {
            do {
                Log.d("test",
                        c.getInt(c.getColumnIndex(ItemManager.KEY_ID_ITEM)) + "," +
                                c.getString(c.getColumnIndex(ItemManager.KEY_NOM_ITEM))
                );
            }
            while (c.moveToNext());
        }
        c.close(); // fermeture du curseur

        // fermeture du gestionnaire
        im.close();

        Intent intent = new Intent(this, ListActivity.class);
        this.finish();
        this.startActivity(intent);
    }

}
