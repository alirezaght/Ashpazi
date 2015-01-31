package com.cross.ashpazi;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.cross.helper.Recepient;

import java.io.IOException;


public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        ImageView img= (ImageView) findViewById(R.id.imageView);
        TextView avalie= (TextView) findViewById(R.id.textView);
        TextView dastur= (TextView) findViewById(R.id.textView2);
        Recepient r= (Recepient) getIntent().getExtras().getSerializable("recepient");
        try {
            Drawable d = Drawable.createFromStream(getAssets().open(r.ax), null);
            img.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
        avalie.setText(r.avalie);
        dastur.setText(r.dastur);

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

