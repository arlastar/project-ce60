package com.example.arlastar.login.api;

public class User2 {
    private Integer id;
    private String search;
    private String first_check ;
    private String second_check ;
    private String third_check  ;
    private String degree  ;
    private String faculty  ;
    private String major  ;


    public Integer getId() {
        return id;
    }

    public User2(String search, String degree ,String faculty ,String major,String first_check,String second_check ,String third_check ){
        this.search = search;
        this.first_check=first_check;
        this.second_check=second_check;
        this.third_check =third_check ;
        this.degree=degree;
        this.faculty=faculty;
        this.major=major;
    }

}
