package com.example.arlastar.login.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Arlastar on 3/15/2018.
 */

public class LoginResponse {
    @SerializedName("Status")
    String Status;

    public String getStatus() {
        return Status;
    }
}
