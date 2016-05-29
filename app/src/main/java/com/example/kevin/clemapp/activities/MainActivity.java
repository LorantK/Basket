package com.example.kevin.clemapp.activities;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.kevin.clemapp.R;
import com.example.kevin.clemapp.parsing.Parser;


public class MainActivity extends Activity {


    String url = "https://www.amazon.fr/gp/product/B01483WYBW/ref=s9_simh_gw_p107_d1_i3?pf_rd_m=A1X6FK5RDHNB96&pf_rd_s=desktop-1&pf_rd_r=F5RETC6DKF2DV67ZBQFS&pf_rd_t=36701&pf_rd_p=863560947&pf_rd_i=desktop";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    public void onClick(View v){
        EditText mEdit = (EditText)findViewById(R.id.editText);
        Parser p = new Parser(this, mEdit.getText().toString());
        p.execute(url);

    }
}
