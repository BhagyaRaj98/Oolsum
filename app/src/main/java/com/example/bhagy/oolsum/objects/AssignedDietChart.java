package com.example.bhagy.oolsum.objects;

public final class AssignedDietChart {


    private String tv_breakfast;
    private String tv_start_time;
    private String tv_end_time;
    private String tv_comments;
    private int iv_time_image;

    public AssignedDietChart(int iv_time_image, String tv_breakfast, String tv_start_time, String tv_end_time, String tv_comments) {

        this.iv_time_image = iv_time_image;
        this.tv_breakfast = tv_breakfast;
        this.tv_start_time = tv_start_time;
        this.tv_end_time = tv_end_time;
        this.tv_comments = tv_comments;
    }


    public String getTv_breakfast() {
        return tv_breakfast;
    }

    public String getTv_start_time() {
        return tv_start_time;
    }

    public String getTv_end_time() {
        return tv_end_time;
    }

    public String getTv_comments() {
        return tv_comments;
    }

    public int getIv_time_image() {
        return iv_time_image;
    }

}
