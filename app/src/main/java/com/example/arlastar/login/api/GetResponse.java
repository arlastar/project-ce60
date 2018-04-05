package com.example.arlastar.login.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

}
