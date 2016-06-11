package com.example.kevin.clemapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kevin.clemapp.R;
import com.example.kevin.clemapp.adapters.ItemAdapter;
import com.example.kevin.clemapp.decorations.DividerItemDecoration;
import com.example.kevin.clemapp.managers.ItemManager;
import com.example.kevin.clemapp.models.Item;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListActivity extends Activity {

    private RecyclerView recyclerView;
    private ItemAdapter itAdapter;
    private ArrayList<Item> itemList = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayout );
        layout.setOrientation(LinearLayout.VERTICAL);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        itAdapter = new ItemAdapter(itemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(itAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                click(view, position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        getItems();

    }

    public void click(View v, int id){
        Intent intent = new Intent(this, ItemViewActivity.class);
        intent.putExtra("id", id);
        this.startActivity(intent);
    }

    private void getItems() {
        ItemManager im = new ItemManager(this);
        im.open();
        Cursor c = im.getItems();
        if (c.moveToFirst()) {
            do {
                itemList.add(new Item(c.getInt(c.getColumnIndex(ItemManager.KEY_ID_ITEM)),
                        c.getString(c.getColumnIndex(ItemManager.KEY_NOM_ITEM)),
                        c.getString(c.getColumnIndex(ItemManager.KEY_PRICE_ITEM)),
                        c.getString(c.getColumnIndex(ItemManager.KEY_SELLER_ITEM)),
                        c.getString(c.getColumnIndex(ItemManager.KEY_URL_ITEM))));
            }
            while (c.moveToNext());
        }
        c.close();
        im.close();
        itAdapter.notifyDataSetChanged();
    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ListActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ListActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }


    }

}
