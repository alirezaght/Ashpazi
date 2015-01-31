package com.cross.ashpazi;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.cross.helper.Dao;
import com.cross.helper.customAdapter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity {
    public static Dao dao;
    public static String version="3";
    private ViewFlipper viewFlipper;
    private float lastX;
    private ImageView[] dots=new ImageView[4];
    private Integer pos;
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener(this));
        this.viewFlipper= (ViewFlipper) findViewById(R.id.viewFlipper);
        dots[0]= (ImageView) findViewById(R.id.dot1);
        dots[1]= (ImageView) findViewById(R.id.dot2);
        dots[2]= (ImageView) findViewById(R.id.dot3);
        dots[3]= (ImageView) findViewById(R.id.dot4);
        dots[0].setImageDrawable(null);
        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.dotselected));
        pos=0;
        dao=new Dao(this);
        try {
            dao.createDataBase();
            dao.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public boolean onTouchEvent(MotionEvent touchevent) {
        return mDetector.onTouchEvent(touchevent);
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


    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private Context context;
        public MyGestureListener(Context context){
            this.context=context;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if(pos==0) {
                Intent irani = new Intent(context, IraniActivity.class);
                startActivity(irani);
            }
            return true;
        }

        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            if(event1.getX()>event2.getX())
            {
                if (viewFlipper.getDisplayedChild() == 3)
                    return false;

                // Next screen comes in from right.
                viewFlipper.setInAnimation(context, R.anim.slide_in_from_right);
                // Current screen goes out from left.
                viewFlipper.setOutAnimation(context, R.anim.slide_out_to_left);

                // Display previous screen.
                viewFlipper.showNext();
                dots[pos].setImageDrawable(null);
                dots[pos].setImageDrawable(getResources().getDrawable(R.drawable.dot));
                pos++;
                if(pos<0)pos=0;
                if(pos>3)pos=3;
                dots[pos].setImageDrawable(null);
                dots[pos].setImageDrawable(getResources().getDrawable(R.drawable.dotselected));
            }
            else if (event1.getX()<event2.getX())
            {
                if (viewFlipper.getDisplayedChild() == 0)
                    return false;

                // Next screen comes in from left.
                viewFlipper.setInAnimation(context, R.anim.slide_in_from_left);
                // Current screen goes out from right.
                viewFlipper.setOutAnimation(context, R.anim.slide_out_to_right);

                // Display next screen.
                viewFlipper.showPrevious();
                dots[pos].setImageDrawable(null);
                dots[pos].setImageDrawable(getResources().getDrawable(R.drawable.dot));
                pos--;
                if(pos<0)pos=0;
                if(pos>3)pos=3;
                dots[pos].setImageDrawable(null);
                dots[pos].setImageDrawable(getResources().getDrawable(R.drawable.dotselected));
            }
            return true;
        }
    }
}

