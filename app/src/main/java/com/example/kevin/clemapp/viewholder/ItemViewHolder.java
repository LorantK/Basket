package com.example.kevin.clemapp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.kevin.clemapp.R;
import com.example.kevin.clemapp.managers.ItemManager;

/**
 * Created by Kevin on 11/06/2016.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

    private TextView t;

    public TextView getT() {
        return t;
    }

    public void setT(TextView t) {
        this.t = t;
    }

    public ItemViewHolder(View itemView){
        super(itemView);
        t= (TextView) itemView.findViewById(R.id.title);
    }

}
