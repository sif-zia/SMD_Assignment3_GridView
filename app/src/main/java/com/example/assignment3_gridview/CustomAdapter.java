package com.example.assignment3_gridview;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    ArrayList<MenuOption> menuOptions;
    Context context;

    public CustomAdapter(Context context, ArrayList<MenuOption> menuOptions) {
        this.menuOptions = menuOptions;
        this.context = context;
    }

    @Override
    public int getCount() {
        return menuOptions.size();
    }

    @Override
    public Object getItem(int i) {
        return menuOptions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.grid_comp,null);
        }

        view.findViewById(R.id.icon).setBackground(getDrawable(context, menuOptions.get(i).getIcon()));
        ((TextView) view.findViewById(R.id.text)).setText(menuOptions.get(i).getText());
        return view;
    }
}
