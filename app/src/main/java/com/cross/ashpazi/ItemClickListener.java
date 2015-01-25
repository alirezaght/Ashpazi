package com.cross.ashpazi;

import android.view.View;
import android.widget.AdapterView;

import com.cross.helper.customAdapter;

/**
 * Created by Navit on 1/24/2015.
 */
public class ItemClickListener implements android.widget.AdapterView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        customAdapter custom= (customAdapter) adapterView.getAdapter();

    }
}
