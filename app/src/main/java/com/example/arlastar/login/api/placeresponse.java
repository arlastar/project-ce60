package com.example.arlastar.login.api;

/**
 * Created by Arlastar on 4/8/2018.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class placeresponse {

    @SerializedName("key")
    private String key;
    @SerializedName("result")
    private List<String> result = null;

    public String getKey() {
        return key;
    }

    public List<String> getResult() {
        return result;
    }
}
