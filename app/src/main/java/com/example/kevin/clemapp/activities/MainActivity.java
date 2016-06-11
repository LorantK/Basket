package com.example.kevin.clemapp.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kevin.clemapp.R;
import com.example.kevin.clemapp.managers.ItemManager;
import com.example.kevin.clemapp.parsing.Parser;
import com.example.kevin.clemapp.tools.Tools;


public class MainActivity extends Activity {


    String url = "https://www.amazon.fr/gp/product/B01483WYBW/ref=s9_simh_gw_p107_d1_i3?pf_rd_m=A1X6FK5RDHNB96&pf_rd_s=desktop-1&pf_rd_r=F5RETC6DKF2DV67ZBQFS&pf_rd_t=36701&pf_rd_p=863560947&pf_rd_i=desktop";
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void onClick(View v){
        Tools t = new Tools(this);
        if(t.isOnline()) {
            pd=ProgressDialog.show(this,"","Please Wait",false);
            EditText mEdit = (EditText) findViewById(R.id.editText);
            Parser p = new Parser(this, mEdit.getText().toString(),pd);
            p.execute(url);

        }
        else {
            Toast toast = Toast.makeText(this, "Pas de connexion", Toast.LENGTH_SHORT);
            toast.show();
        }

    }



}
