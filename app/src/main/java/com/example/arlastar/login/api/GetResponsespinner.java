package com.example.arlastar.login.api;

/**
 * Created by Arlastar on 4/7/2018.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetResponsespinner {
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("result")
    @Expose
    private List<String> result = null;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
