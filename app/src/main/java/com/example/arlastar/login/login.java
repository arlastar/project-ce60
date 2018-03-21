package com.example.arlastar.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arlastar.login.api.LoginResponse;
import com.example.arlastar.login.api.User;
import com.example.arlastar.login.api.Usercheck;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends AppCompatActivity  {
    Button bLogin;
    EditText etUsername,etPassword;
    String email,password;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http:111.111.111.11")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Usercheck login =retrofit.create(Usercheck.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }



  public void login(){
//        email = etUsername.getText().toString();
//        password=etPassword.getText().toString();
//        Log.i("LoginDetail","email :"+email+"\npassword :"+password);
//        User info = new User(email,password);
//        Call<LoginResponse> call = login.createAccount(info);
//        call.enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//
//            }
//        });

        Intent intent =new Intent(login.this,Mainfunction.class);
        startActivity(intent);

}


}