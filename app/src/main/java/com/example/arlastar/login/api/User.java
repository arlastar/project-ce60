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

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public User(String student_id,int check){
        this.student_id = student_id;
        this.check=check;
    }
}
