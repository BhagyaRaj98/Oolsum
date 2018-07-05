package com.example.bhagy.oolsum.objects;


public final class User {
    private String uid;
    private String emailId;
    private String mobileNumber;
    private String displayName;
    private String registrationMethod;
    private String socialUid;
    private String profilePictureUrl;
    private String providerId;
    private boolean emailVerified;

    public String getUid() {
        return uid;
    }

    public User setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getEmailId() {
        return emailId;
    }

    public User setEmailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public User setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public User setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public String getRegistrationMethod() {
        return registrationMethod;
    }

    public User setRegistrationMethod(String registrationMethod) {
        this.registrationMethod = registrationMethod;
        return this;
    }

    public String getSocialUid() {
        return socialUid;
    }

    public User setSocialUid(String socialUid) {
        this.socialUid = socialUid;
        return this;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public User setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
        return this;
    }

    public String getProviderId() {
        return providerId;
    }

    public User setProviderId(String providerId) {
        this.providerId = providerId;
        return this;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public User setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
        return this;
    }
}
