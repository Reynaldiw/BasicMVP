package com.reynaldiwijaya.basicmvp.Model;

import com.google.gson.annotations.SerializedName;

public class SingleUserResponse {

    @SerializedName("data")
    private UserData userData;

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
