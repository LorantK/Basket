package com.example.kevin.clemapp.activities;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kevin.clemapp.R;
import com.example.kevin.clemapp.managers.ItemManager;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayout );
        layout.setOrientation(LinearLayout.VERTICAL);

        ItemManager im = new ItemManager(this);
        im.open();
        Cursor c = im.getItems();
        if (c.moveToFirst())
        {
            do {
                TextView t = new TextView(this);
                t.setText(c.getString(c.getColumnIndex(ItemManager.KEY_NOM_ITEM)));
                layout.addView(t);
            }
            while (c.moveToNext());
        }
        c.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
