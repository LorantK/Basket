    package com.example.kevin.clemapp.activities;

    import android.content.Intent;
    import android.database.Cursor;
    import android.support.v7.app.ActionBarActivity;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.EditText;
    import android.widget.RelativeLayout;
    import android.widget.TextView;

    import com.example.kevin.clemapp.R;
    import com.example.kevin.clemapp.managers.ItemManager;
    import com.example.kevin.clemapp.models.Item;
    import com.example.kevin.clemapp.parsing.Parser;

    import org.w3c.dom.Text;

    public class ItemActivity extends ActionBarActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_item);

            Intent intent = getIntent();
            TextView nametv = (TextView) findViewById(R.id.name);
            nametv.setText(getIntent().getStringExtra("name"));
            TextView sellertv = (TextView) findViewById(R.id.seller);
            sellertv.setText(getIntent().getStringExtra("seller"));
            TextView pricetv = (TextView) findViewById(R.id.price);
            pricetv.setText(getIntent().getStringExtra("price"));
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.content);
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

        public void onClick(View v){
            ItemManager im = new ItemManager(this);
            im.open();
            im.addItem(new Item(0,
                    getIntent().getStringExtra("name"),
                    getIntent().getStringExtra("price"),
                    getIntent().getStringExtra("seller"),
                    ""));

            Cursor c = im.getItems();
            if (c.moveToFirst())
            {
                do {
                    Log.d("test",
                            c.getInt(c.getColumnIndex(ItemManager.KEY_ID_ITEM)) + "," +
                                    c.getString(c.getColumnIndex(ItemManager.KEY_NOM_ITEM))
                    );
                }
                while (c.moveToNext());
            }
            c.close(); // fermeture du curseur

    // fermeture du gestionnaire
            im.close();
        }

    }
