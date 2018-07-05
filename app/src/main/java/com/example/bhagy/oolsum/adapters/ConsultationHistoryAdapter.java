package com.example.bhagy.oolsum.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.activities.ConsultationDetailsActivity;
import com.example.bhagy.oolsum.activities.ViewDietChartActivity;
import com.example.bhagy.oolsum.objects.Consultation;

import java.util.ArrayList;

public class ConsultationHistoryAdapter extends RecyclerView.Adapter<ConsultationHistoryAdapter.ConsultationViewHolder> {

    private ArrayList<Consultation> consultationItems;

    public ConsultationHistoryAdapter(Context context, ArrayList<Consultation> ConsultationItems) {
        this.consultationItems = ConsultationItems;
    }

    @NonNull
    @Override
    public ConsultationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.consultation_history_item, parent, false);

        return new ConsultationViewHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultationViewHolder holder, int position) {

        Consultation currentConsultation = consultationItems.get(position);
        holder.patientNameView.setText(currentConsultation.getPatientName());
        holder.consultationTimeView.setText(currentConsultation.getConsultationTime());
        holder.consultationFeeView.setText(currentConsultation.getConsultationFee());
    }

    @Override
    public int getItemCount() {
        return consultationItems.size();
    }


    public class ConsultationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final Context mContext;
        TextView patientNameView, consultationTimeView, consultationFeeView;
        RelativeLayout consultationDetailsView;
        ImageView dietChartView;
        ImageView chatHistoryView;

        ConsultationViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();

            patientNameView = itemView.findViewById(R.id.tv_patient_name);
            consultationTimeView = itemView.findViewById(R.id.tv_consultation_time);
            consultationFeeView = itemView.findViewById(R.id.tv_consultation_fee);

            consultationDetailsView = itemView.findViewById(R.id.consultation_details);
            consultationDetailsView.setOnClickListener(this);

            dietChartView = itemView.findViewById(R.id.view_diet_chart);
            dietChartView.setOnClickListener(this);

            chatHistoryView = itemView.findViewById(R.id.chat_history_bubble);
            chatHistoryView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.consultation_details:
                    Intent consultationDetailsIntent = new Intent(mContext, ConsultationDetailsActivity.class);
                    mContext.startActivity(consultationDetailsIntent);
                    break;

                case R.id.view_diet_chart:
                    Intent viewDietChartIntent = new Intent(mContext, ViewDietChartActivity.class);
                    mContext.startActivity(viewDietChartIntent);
                    break;

                case R.id.chat_history_bubble:
//  Chat History Activity should be created and connected

//                    Intent chatHistoryIntent = new Intent(mContext, chatHistoryActivity.class);
//                    mContext.startActivity(chatHistoryIntent);
                    break;

            }
        }

    }

}