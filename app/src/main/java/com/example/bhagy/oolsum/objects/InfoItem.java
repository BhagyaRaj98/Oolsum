package com.example.bhagy.oolsum.objects;

public final class InfoItem {
    private int infoIconId;
    private String infoName;
    private String infoValue;

    public InfoItem(int iconId, String name, String value) {
        this.infoIconId = iconId;
        this.infoName = name;
        this.infoValue = value;
    }

    public int getInfoIconId() {
        return infoIconId;
    }

    public String getInfoName() {
        return infoName;
    }

    public String getInfoValue() {
        return infoValue;
    }

}
