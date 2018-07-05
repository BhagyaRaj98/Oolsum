package com.example.bhagy.oolsum.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bhagy.oolsum.objects.AssignedDietChart;
import com.example.bhagy.oolsum.R;

import java.util.List;

public class DietChartAdapter extends RecyclerView.Adapter<DietChartAdapter.DietChartViewHolder> {

    private static final String TAG = "DietChartAdapter";;
    private List<AssignedDietChart> dietChartList;


    public DietChartAdapter(Context context, List<AssignedDietChart> dietChartList) {
        this.dietChartList = dietChartList;
    }


    @NonNull
    @Override
    public DietChartAdapter.DietChartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View recyclerItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dietchart_list_item, parent, false);
        return new DietChartViewHolder(recyclerItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DietChartAdapter.DietChartViewHolder holder, int position) {

        AssignedDietChart currentDietChart = dietChartList.get(position);
        assert currentDietChart != null;

        holder.iv_time_image.setImageResource(currentDietChart.getIv_time_image());

        holder.tv_breakfast.setText(currentDietChart.getTv_breakfast());

        holder.tv_start_time.setText(currentDietChart.getTv_start_time());

        holder.tv_end_time.setText(currentDietChart.getTv_end_time());

        holder.tv_comments.setText(currentDietChart.getTv_comments());

    }

    @Override
    public int getItemCount() {
        return dietChartList.size();
    }

    class DietChartViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        TextView tv_breakfast, tv_start_time, tv_end_time, tv_comments;
        ImageView iv_time_image;

        DietChartViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();

            tv_breakfast = itemView.findViewById(R.id.tv_breakfast);
            tv_start_time = itemView.findViewById(R.id.tv_start_time);
            tv_end_time = itemView.findViewById(R.id.tv_end_time);
            tv_comments = itemView.findViewById(R.id.tv_comments);
            iv_time_image = itemView.findViewById(R.id.iv_time_image);
        }

    }

}
