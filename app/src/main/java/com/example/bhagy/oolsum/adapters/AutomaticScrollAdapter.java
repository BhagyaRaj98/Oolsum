package com.example.bhagy.oolsum.adapters;


import com.example.bhagy.oolsum.R;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AutomaticScrollAdapter extends PagerAdapter {

    private int[] images;
    private String[] steps;
    private String[] instructions;
    private Context mContext;

    public AutomaticScrollAdapter(Context mContext, int[] images, String[] steps, String[] instructions) {
        this.mContext = mContext;
        this.images = images;
        this.steps = steps;
        this.instructions = instructions;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View itemView = LayoutInflater.from(mContext).inflate(R.layout.user_home_image_slider_item, container, false);
        ImageView imageView = itemView.findViewById(R.id.slider_image);
        imageView.setImageResource(images[position]);

        TextView stepNumView = itemView.findViewById(R.id.slider_step_num);
        stepNumView.setText(steps[position]);

        TextView stepInstView = itemView.findViewById(R.id.slider_step_instruction);
        stepInstView.setText(instructions[position]);

        //add item.xml to viewpager
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // Remove viewpager_item.xml from ViewPager
        container.removeView((View) object);
    }

}