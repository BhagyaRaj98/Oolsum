package com.example.bhagy.oolsum.objects;

public final class Independent {
    private String availability;
    private String rating;
    private int independentImageId;
    private String doctorName;
    private String qualification;
    private String experience;
    private String specialisation;
    private String fee;

    public Independent(String status, String rating, int imgId, String doctorName, String qualification,
                       String experience, String specialisation, String fee) {

        this.availability = status;
        this.rating = rating;
        this.independentImageId = imgId;
        this.doctorName = doctorName;
        this.qualification = qualification;
        this.experience = experience;
        this.specialisation = specialisation;
        this.fee = fee;
    }

    public String getAvailability() {
        return availability;
    }

    public String getRating() {
        return rating;
    }

    public int getIndependentImageId() {
        return independentImageId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getQualification() {
        return qualification;
    }

    public String getExperience() {
        return experience;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public String getFee() {
        return fee;
    }
}
