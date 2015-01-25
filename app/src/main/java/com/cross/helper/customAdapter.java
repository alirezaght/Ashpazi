package com.cross.helper;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cross.ashpazi.R;

import java.util.List;

/**
 * Created by Navit on 1/24/2015.
 */
public class customAdapter extends BaseAdapter {
    public Drawable[] imageSrc;
    public String[] itemText;
    public Context context;
    public customAdapter(Context context,Drawable[] imageSrc,String[] itemText){
        this.context=context;
        this.imageSrc=imageSrc;
        this.itemText=itemText;
    }
    @Override
    public int getCount() {
        return itemText.length;
    }

    @Override
    public Object getItem(int i) {
        return itemText[i];
    }

    @Override
    public long getItemId(int i) {
        return itemText[i].hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
            view=LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
        ImageView img= (ImageView) view.findViewById(R.id.itemImage);
        TextView tv= (TextView) view.findViewById(R.id.itemText);
        tv.setText(itemText[i]);
        img.setImageDrawable(imageSrc[i]);

        return view;
    }
}
