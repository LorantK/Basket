package com.example.kevin.clemapp.parsing;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.kevin.clemapp.activities.ItemEditActivity;
import com.example.kevin.clemapp.activities.MainActivity;
import com.example.kevin.clemapp.models.Item;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Kevin on 18/04/2016.
 */
public class Parser extends AsyncTask<String, Void, String> {

    private Activity activity;
    private String url;
    private Item i;
    private ProgressDialog pd;

    public Parser(Activity ac,String s,ProgressDialog pd){
        Log.d("Parsing",s);
        this.activity = ac;
        this.pd = pd;
        this.url = "https://www.amazon.fr/gp/product/B01483WYBW/ref=s9_simh_gw_p107_d1_i3?pf_rd_m=A1X6FK5RDHNB96&pf_rd_s=desktop-1&pf_rd_r=F5RETC6DKF2DV67ZBQFS&pf_rd_t=36701&pf_rd_p=863560947&pf_rd_i=desktop";
    }

   @Override
    protected String doInBackground(String... params){
       Document doc;
       try {
           doc = Jsoup.connect(url).get();
           URI uri = new URI(url);
           String name = doc.getElementById("productTitle").text();
           String price = doc.getElementById("priceblock_ourprice").text();
           String seller = uri.getHost();
           this.i = new Item(0,name,price,seller,url);
           return this.i.toString();
       } catch (IOException  | URISyntaxException e) {
           e.printStackTrace();
       }
       return "";
    }

    @Override
    protected void onPostExecute(String result) {
        this.pd.dismiss();
        Intent intent = new Intent(this.activity, ItemEditActivity.class);
        intent.putExtra("name",this.i.getName());
        intent.putExtra("price",this.i.getPrice());
        intent.putExtra("seller",this.i.getSeller());
        activity.startActivity(intent);

    }

}
