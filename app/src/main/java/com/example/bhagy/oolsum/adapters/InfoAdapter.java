package com.example.bhagy.oolsum.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.objects.InfoItem;

import java.util.List;


public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private static final String TAG = "InfoAdapter";
    private List<InfoItem> infoItems;

    public InfoAdapter(Context context, List<InfoItem> infoItems) {
        this.infoItems = infoItems;
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_list_item, parent, false);
        return new InfoViewHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {

        InfoItem currentInfo = infoItems.get(position);

        holder.infoImageView.setImageResource(currentInfo.getInfoIconId());

        holder.infoNameView.setText(currentInfo.getInfoName());

        holder.infoValueView.setText(currentInfo.getInfoValue());
    }

    @Override
    public int getItemCount() {
        return infoItems.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        ImageView infoImageView;
        TextView infoNameView;
        TextView infoValueView;


        public InfoViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();

            infoImageView = itemView.findViewById(R.id.detail_icon);
            infoNameView = itemView.findViewById(R.id.detail_name);
            infoValueView = itemView.findViewById(R.id.detail_value);

        }

    }
}
