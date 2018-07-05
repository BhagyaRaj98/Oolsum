package com.example.bhagy.oolsum.objects;

public final class Hospital {

    private int hospitalImageId;
    private String averageCost;
    private String dietitiansOnline;
    private String hospitalRating;
    private String hospitalSpecialities;
    private String hospitalName;
    private String hospitalLocation;


    Hospital(HospitalBuilder hospitalBuilder) {

        this.hospitalImageId = hospitalBuilder.hospitalImageId;
        this.averageCost = hospitalBuilder.averageCost;
        this.dietitiansOnline = hospitalBuilder.dietitiansOnline;
        this.hospitalRating = hospitalBuilder.hospitalRating;
        this.hospitalSpecialities = hospitalBuilder.hospitalSpecialities;
        this.hospitalName = hospitalBuilder.hospitalName;
        this.hospitalLocation = hospitalBuilder.hospitalLocation;

    }

    public int getHospitalImageId() {
        return hospitalImageId;
    }

    public String getAverageCost() {
        return averageCost;
    }

    public String getDietitiansOnline() {
        return dietitiansOnline;
    }

    public String getHospitalRating() {
        return hospitalRating;
    }

    public String getSpecialities() {
        return hospitalSpecialities;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getHospitalLocation() {
        return hospitalLocation;
    }


    public static class HospitalBuilder {

        private int hospitalImageId;
        private String averageCost;
        private String dietitiansOnline;
        private String hospitalRating;
        private String hospitalSpecialities;
        private String hospitalName;
        private String hospitalLocation;

        public HospitalBuilder(String name, String location, String specialities) {
            this.hospitalName = name;
            this.hospitalLocation = location;
            this.hospitalSpecialities = specialities;
        }

        public HospitalBuilder addImage(int imgId) {
            this.hospitalImageId = imgId;
            return this;
        }

        public HospitalBuilder addAverageCost(String avgCost) {
            this.averageCost = avgCost;
            return this;
        }

        public HospitalBuilder addDietitiansOnline(String numOfDietitians) {
            this.dietitiansOnline = numOfDietitians;
            return this;
        }

        public HospitalBuilder addRating(String rating) {
            this.hospitalRating = rating;
            return this;
        }

        public Hospital buildHospital() {
            return new Hospital(this);
        }

    }
}
