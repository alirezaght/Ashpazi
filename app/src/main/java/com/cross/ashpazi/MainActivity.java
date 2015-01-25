package com.cross.ashpazi;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.cross.helper.Dao;
import com.cross.helper.customAdapter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity {
    public Dao dao;
    public static String version="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res=getResources();
        String[] itemText=res.getStringArray(R.array.category);
        Drawable[] imageSrc=new Drawable[]{getResources().getDrawable(R.drawable.ic_dessert),getResources().getDrawable(R.drawable.ic_appetizer)
        ,getResources().getDrawable(R.drawable.ic_maincourse),getResources().getDrawable(R.drawable.ic_bread),getResources().getDrawable(R.drawable.ic_whattocook)};
        ListView lv= (ListView) findViewById(R.id.listView);
        lv.setAdapter(new customAdapter(this,imageSrc,itemText));
        lv.setOnItemClickListener(new ItemClickListener());
        dao=new Dao(this);
        try {
            dao.createDataBase();
            dao.openDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
}
