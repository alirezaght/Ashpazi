package com.cross.ashpazi;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.cross.helper.customAdapter;

/**
 * Created by Navit on 1/24/2015.
 */
public class ItemClickListener implements android.widget.AdapterView.OnItemClickListener {

    private Context context;
    public ItemClickListener(Context context) {
        this.context=context;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        customAdapter custom= (customAdapter) adapterView.getAdapter();
        Intent detail=new Intent(context,DetailActivity.class);
        detail.putExtra("recepient",custom.list.get(i));
        context.startActivity(detail);

    }
}
