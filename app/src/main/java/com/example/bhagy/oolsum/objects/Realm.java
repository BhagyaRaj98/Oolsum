package com.example.bhagy.oolsum.objects;

import java.util.Collection;
import java.util.Collections;

/*
 * Security realm, to hold logged in user details.

 */
public final class Realm {

    private String userId;

    private String email;

    private Collection<String> roles = Collections.emptyList();

    private boolean authenticated;

    public String getUserId() {
        return userId;
    }

    public Realm setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Realm setEmail(String email) {
        this.email = email;
        return this;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public Realm setRoles(Collection<String> roles) {
        this.roles = roles;
        return this;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public Realm setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
        return this;
    }

    @Override
    public String toString() {
        return "Realm [userId=" + userId + ", email=" + email + ", roles=" + roles + ", authenticated=" + authenticated
                + "]";
    }

}
