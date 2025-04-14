package com.starwar.pinvegetables;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NowVegetablesAdapter extends BaseAdapter {

    private Context context;
    private List<VegetableListInfo> listInfos;

    public NowVegetablesAdapter(Context context, List<VegetableListInfo> listInfos){
        this.context = context;
        this.listInfos = listInfos;
    }


    @Override
    public int getCount() {
        return listInfos.size();
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
            convertView = View.inflate(context, R.layout.item_my_list, null);
            TextView tv_item_status_context = convertView.findViewById(R.id.tv_item_status_context);
            TextView tv_item_title_context = convertView.findViewById(R.id.tv_item_title_context);
            tv_item_status_context.setText(this.listInfos.get(position).getStatus());
            tv_item_title_context.setText(this.listInfos.get(position).getName());
        }
        return convertView;
    }
}
