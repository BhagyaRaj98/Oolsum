package com.example.bhagy.oolsum.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bhagy.oolsum.R;

import java.util.ArrayList;

public class HorizontalRowAdapter extends BaseAdapter {

    private static final String TAG = "HorizontalRowAdapter";
    private Context mContext;
    private ArrayList<String> detailNames, detailValues;

    public HorizontalRowAdapter(Context mContext, ArrayList<String> detailNames, ArrayList<String> detailValues) {
        this.mContext = mContext;
        this.detailNames = detailNames;
        this.detailValues = detailValues;
    }


    @Override
    public int getCount() {
        return detailNames.size();
    }

    @Override
    public Object getItem(int position) {
        return detailNames.get(position)+": "+detailValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(mContext).inflate(R.layout.consultation_details_item, parent, false);
        }

        TextView detailNameView = listItemView.findViewById(R.id.tv_detail_name);
        detailNameView.setText(detailNames.get(position));

        TextView detailValueView = listItemView.findViewById(R.id.tv_detail_value);
        detailValueView.setText(detailValues.get(position));

        return listItemView;
    }

}