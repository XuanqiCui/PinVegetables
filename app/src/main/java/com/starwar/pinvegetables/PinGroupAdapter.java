package com.starwar.pinvegetables;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class PinGroupAdapter extends BaseAdapter {

    private Context context;
    private List<PinGroup> pinGroupList;

    public PinGroupAdapter(Context context, List<PinGroup> pinGroupList) {
        this.context = context;
        this.pinGroupList = pinGroupList;
    }

    @Override
    public int getCount() {
        return pinGroupList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = View.inflate(context, R.layout.item_pin_group, null);
            TextView tv_item_pin_group = convertView.findViewById(R.id.tv_pin_group_item);
            tv_item_pin_group.setText(pinGroupList.get(position).getUsername());
            return convertView;
        }
        return convertView;
    }
}
