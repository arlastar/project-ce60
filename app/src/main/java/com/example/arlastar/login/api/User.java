package com.example.arlastar.login.api;

/**
 * Created by Arlastar on 3/15/2018.
 */

public class User {
    private Integer id;
    private String email;
    private String password;
    private String student_id;
    private int check;
    private String day;
    private String period;
    private String place;


    public Integer getId() {
        return id;
    }

    public User(String student_id,int check, String day,String period,String place){
        this.student_id = student_id;
        this.check=check;
        this.day=day;
        this.period=period;
        this.place=place;

    }

}

