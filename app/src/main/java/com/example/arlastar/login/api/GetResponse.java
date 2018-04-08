package com.example.arlastar.login.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Arlastar on 3/15/2018.
 */

public class GetResponse {

    @SerializedName("student_id")
    String student_id;
    @SerializedName("name_title")
     String nameTitle;
    @SerializedName("first_name")
    String firstName;
    @SerializedName("last_name")
    String lastName;
    @SerializedName("faculty")
    String faculty;
    @SerializedName("major")
    String major;
    @SerializedName("image")
    String image;
    @SerializedName("id")
    Integer id;
    @SerializedName("first_check")
    Boolean firstCheck;
    @SerializedName("second_check")
    Boolean secondCheck;
    @SerializedName("third_check")
    Boolean thirdCheck;
    @SerializedName("day")
    public String day;
    @SerializedName("period")
    public String period;
    @SerializedName("load_place")
    public String loadPlace;
    @SerializedName("place")
    public String place;
    @SerializedName("order")
    public int order;


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

    public String getStudent_id() {
        return student_id;
    }



    public String getNameTitle() {
        return nameTitle;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getFaculty() {
        return faculty;
    }


    public String getMajor() {
        return major;
    }


    public String getImage() {
        return image;
    }


    public Integer getId() {
        return id;
    }


    public Boolean getFirstCheck() {return firstCheck;}

    public Boolean getSecondCheck() {
        return secondCheck;
    }


    public Boolean getThirdCheck() {
        return thirdCheck;
    }

    public int getOrder() {
        return order;
    }
    public String getDay() {
        return day;
    }

    public String getPeriod() {
        return period;
    }

    public String getLoadPlace() {
        return loadPlace;
    }

    public String getPlace() {
        return place;
    }

}
