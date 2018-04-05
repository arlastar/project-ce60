package com.example.arlastar.login.api;

public class User2 {
    private Integer id;
    private String student_id;
    private String first_check ;
    private String second_check ;
    private String third_check  ;


    public Integer getId() {
        return id;
    }

    public User2(String student_id,String first_check,String second_check ,String third_check ){
        this.student_id = student_id;
        this.first_check=first_check;
        this.second_check=second_check;
        this.third_check =third_check ;
    }

}
