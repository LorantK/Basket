package com.example.kevin.clemapp.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kevin.clemapp.R;

public class ItemActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent intent = getIntent();
        TextView nametv = new TextView(this);
        nametv.setText(getIntent().getStringExtra("name"));
        TextView sellertv = new TextView(this);
        sellertv.setText(getIntent().getStringExtra("seller"));
        TextView pricetv = new TextView(this);
        pricetv.setText(getIntent().getStringExtra("price"));
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.content);
        layout.addView(nametv);
        layout.addView(sellertv);
        layout.addView(pricetv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item, menu);
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
