package com.example.kevin.clemapp.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Kevin on 29/05/2016.
 */
public class Tools {
    private Context c;

    public Tools(Context c){
        this.c = c;
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }




}
