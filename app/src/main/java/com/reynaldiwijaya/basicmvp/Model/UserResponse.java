package com.reynaldiwijaya.basicmvp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {

    @SerializedName("data")
    private List<UserData> userDataList;

    public List<UserData> getUserDataList() {
        return userDataList;
    }

    public void setUserDataList(List<UserData> userDataList) {
        this.userDataList = userDataList;
    }
}
