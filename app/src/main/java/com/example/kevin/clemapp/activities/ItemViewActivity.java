package com.example.kevin.clemapp.activities;

import android.app.Activity;
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

public class ItemViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
        int id = getIntent().getIntExtra("id", 0);
        //Log.d("getID","ID = "+id);
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


}
