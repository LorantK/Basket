package com.example.kevin.clemapp.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kevin.clemapp.R;
import com.example.kevin.clemapp.managers.ItemManager;
import com.example.kevin.clemapp.models.Item;

public class ItemViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
        int id = getIntent().getIntExtra("id",0);
        Log.d("getID","ID = "+id);
        ItemManager im = new ItemManager(this);
        im.open();
        Item i = im.getItem(id);
        TextView nametv = (TextView) findViewById(R.id.name);
        nametv.setText(i.getName());
        TextView sellertv = (TextView) findViewById(R.id.seller);
        sellertv.setText(i.getSeller());
        TextView pricetv = (TextView) findViewById(R.id.price);
        pricetv.setText(i.getPrice());
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.content);
        im.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_view, menu);
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
}
