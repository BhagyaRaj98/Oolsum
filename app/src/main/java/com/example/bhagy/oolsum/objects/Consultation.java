package com.example.bhagy.oolsum.objects;

public final class Consultation {
    private  String patientName,consultationTime,consultationFee;

    public  Consultation(String patientName,String consultationTime,String consultationFee){
        this.patientName = patientName;
        this.consultationTime = consultationTime;
        this.consultationFee = consultationFee;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getConsultationTime() {
        return consultationTime;
    }

    public String getConsultationFee() {
        return consultationFee;
    }
}
