package com.cross.helper;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cross.ashpazi.R;

import java.io.IOException;
import java.util.List;

/**
 * Created by Navit on 1/24/2015.
 */
public class customAdapter extends BaseAdapter {
    public List<Recepient> list;
    public Context context;
    private WindowManager windowManager;
    public customAdapter(Context context,WindowManager windowManager,List<Recepient> list){
        this.context=context;
        this.list=list;
        this.windowManager=windowManager;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
            view=LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
        ImageView img= (ImageView) view.findViewById(R.id.itemImage);
        TextView tv= (TextView) view.findViewById(R.id.itemText);
        tv.setText(list.get(i).name);
        try {
            DisplayMetrics metrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(metrics);
            String preFix="xhdp_";
            switch(metrics.densityDpi){
                case DisplayMetrics.DENSITY_LOW:
                    preFix="ldp_";
                    break;
                case DisplayMetrics.DENSITY_MEDIUM:
                    preFix="mdp_";
                    break;
                case DisplayMetrics.DENSITY_HIGH:
                    preFix="hdp_";
                    break;

            }
            Drawable d = Drawable.createFromStream(context.getAssets().open(preFix+list.get(i).ax), null);
            img.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }
}
