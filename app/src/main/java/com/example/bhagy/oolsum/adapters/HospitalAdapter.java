package com.example.bhagy.oolsum.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bhagy.oolsum.activities.DietitianListActivity;
import com.example.bhagy.oolsum.activities.HospitalsInfoActivity;
import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.objects.Hospital;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder> {

    private static final String TAG = "HospitalAdapter";
    private List<Hospital> hospitals;

    public HospitalAdapter(Context context, List<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

    @NonNull
    @Override
    public HospitalAdapter.HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View recyclerItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospitals_list_item, parent, false);
        return new HospitalViewHolder(recyclerItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.HospitalViewHolder holder, int position) {

        Hospital currentHospital = hospitals.get(position);
        assert currentHospital != null;

        holder.hospitalImageView.setImageResource(currentHospital.getHospitalImageId());

        holder.averageCostView.setText(currentHospital.getAverageCost());
        //holder.averageCostView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rupee, 0, 0, 0);

        holder.numOfDietitiansView.setText(currentHospital.getDietitiansOnline());

        holder.hospitalRatingView.setText(currentHospital.getHospitalRating());
        //holder.hospitalRatingView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rating_star, 0, 0, 0);

        holder.hospitalSpecialitiesView.setText(currentHospital.getSpecialities());

        holder.hospitalNameView.setText(currentHospital.getHospitalName());

        holder.hospitalLocationView.setText(currentHospital.getHospitalLocation());

    }


    @Override
    public int getItemCount() {
        return hospitals.size();
    }


    public class HospitalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final Context mContext;
        ImageView infoIconView;
        ImageView hospitalImageView;

        TextView averageCostView;
        TextView numOfDietitiansView;
        TextView hospitalRatingView;
        TextView hospitalSpecialitiesView;

        View nameLocationView;
        TextView hospitalNameView;
        TextView hospitalLocationView;

        HospitalViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();

            infoIconView = itemView.findViewById(R.id.hospital_info);
            infoIconView.setOnClickListener(this);

            hospitalImageView = itemView.findViewById(R.id.hospital_img);
            hospitalImageView.setOnClickListener(this);

            averageCostView = itemView.findViewById(R.id.tv_avg_cost);
            numOfDietitiansView = itemView.findViewById(R.id.tv_dietitians_online);
            hospitalRatingView = itemView.findViewById(R.id.tv_hospital_ratings);
            hospitalSpecialitiesView = itemView.findViewById(R.id.tv_hospital_specialities);

            nameLocationView = itemView.findViewById(R.id.hospital_name_location);
            nameLocationView.setOnClickListener(this);

            hospitalNameView = itemView.findViewById(R.id.tv_hospital_name);
            hospitalLocationView = itemView.findViewById(R.id.tv_hospital_location);

        }

        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.hospital_info:
                    Intent hospitalInfoIntent = new Intent(mContext, HospitalsInfoActivity.class);
                    mContext.startActivity(hospitalInfoIntent);
                    break;

                case R.id.hospital_img:
                case R.id.hospital_name_location:
                    Intent dietitianListIntent = new Intent(mContext, DietitianListActivity.class);
                    mContext.startActivity(dietitianListIntent);
                    break;

            }
        }

    }
}
