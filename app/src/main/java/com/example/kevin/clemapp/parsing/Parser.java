package com.example.kevin.clemapp.parsing;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.kevin.clemapp.activities.ItemActivity;
import com.example.kevin.clemapp.models.Item;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Kevin on 18/04/2016.
 */
public class Parser extends AsyncTask<String, Void, String> {

    private Activity activity;
    private String url;
    private Item i;

    public Parser(Activity ac,String s){
        Log.d("Parsing",s);
        this.activity = ac;
        this.url = "https://www.amazon.fr/gp/product/B01483WYBW/ref=s9_simh_gw_p107_d1_i3?pf_rd_m=A1X6FK5RDHNB96&pf_rd_s=desktop-1&pf_rd_r=F5RETC6DKF2DV67ZBQFS&pf_rd_t=36701&pf_rd_p=863560947&pf_rd_i=desktop";
    }

    public String getSeller(String url){
        return null;
    }

   /* @Override
    protected String doInBackground(String... params) {
        URL siteUrl = null;
        URLConnection siteConnection;
        BufferedReader in;
        String rl = null;
        StringBuilder sb = new StringBuilder();
        try {
            siteUrl = new URL(params[0]);
            siteConnection = siteUrl.openConnection();
            in = new BufferedReader(new InputStreamReader(siteConnection.getInputStream()));


            // Récupération du code HTML du site ligne par ligne
            while((rl = in.readLine()) != null){
                sb.append(rl);
                // Test si le l'AsyncTask est cancel pour annuler
                // la lecture du site
                if(isCancelled()){
                    in.close();
                    return null;
                }
            }

            // Fermeture du BufferReader
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }*/


   @Override
    protected String doInBackground(String... params){
       Document doc;
       try {
           doc = Jsoup.connect(url).get();

           String name = doc.getElementById("productTitle").text();
           String price = doc.getElementById("priceblock_ourprice").text();
           String seller = "Amazon";
           this.i = new Item(name,price,seller,url);
           return this.i.toString();
       } catch (IOException e) {
           e.printStackTrace();
       }
       return "";
    }

    @Override
    protected void onPostExecute(String result) {

        System.out.println(result);
        Intent intent = new Intent(this.activity, ItemActivity.class);
        intent.putExtra("name",i.getName());
        intent.putExtra("price",i.getPrice());
        intent.putExtra("seller",i.getSeller());
        activity.startActivity(intent);

    }

}
