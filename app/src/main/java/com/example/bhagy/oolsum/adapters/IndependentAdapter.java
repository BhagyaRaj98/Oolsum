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

import com.example.bhagy.oolsum.activities.IndependentsInfoActivity;
import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.objects.Independent;

import java.util.List;

public class IndependentAdapter extends RecyclerView.Adapter<IndependentAdapter.IndependentViewHolder> {

    private static final String TAG = "IndependentAdapter";
    private List<Independent> independents;

    public IndependentAdapter(Context context, List<Independent> independents) {
        this.independents = independents;
    }

    @NonNull
    @Override
    public IndependentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.independents_list_item, parent, false);
        return new IndependentViewHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IndependentViewHolder holder, int position) {

        Independent currentListItem = independents.get(position);

        holder.availabilityView.setText(currentListItem.getAvailability());
        holder.ratingView.setText(currentListItem.getRating());
        holder.independentImageView.setImageResource(currentListItem.getIndependentImageId());
        holder.doctorNameView.setText(currentListItem.getDoctorName());
        holder.qualificationView.setText(currentListItem.getQualification());
        holder.experienceView.setText(currentListItem.getExperience());
        holder.specialisationView.setText(currentListItem.getSpecialisation());
        holder.feeView.setText(currentListItem.getFee());
    }

    @Override
    public int getItemCount() {
        return independents.size();
    }

    public class IndependentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final Context mContext;
        ImageView infoIconView;

        TextView availabilityView;
        TextView ratingView;
        ImageView independentImageView;
        TextView doctorNameView;
        TextView qualificationView;
        TextView experienceView;
        TextView specialisationView;
        TextView feeView;


        public IndependentViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();

            infoIconView = itemView.findViewById(R.id.independent_info);
            infoIconView.setOnClickListener(this);

            availabilityView = itemView.findViewById(R.id.tv_status);
            ratingView = itemView.findViewById(R.id.tv_independent_rating);
            independentImageView = itemView.findViewById(R.id.profile_image);
            doctorNameView = itemView.findViewById(R.id.tv_independent_name);
            qualificationView = itemView.findViewById(R.id.tv_qualification);
            experienceView = itemView.findViewById(R.id.tv_experience);
            specialisationView = itemView.findViewById(R.id.tv_specialities);
            feeView = itemView.findViewById(R.id.tv_fee);

        }

        @Override
        public void onClick(View view) {
            Intent independentInfoIntent = new Intent(mContext, IndependentsInfoActivity.class);
            mContext.startActivity(independentInfoIntent);

        }
    }
}
