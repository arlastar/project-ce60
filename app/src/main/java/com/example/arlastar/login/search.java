package com.example.arlastar.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class search extends AppCompatActivity implements View.OnClickListener {
    EditText etStudentID;
    Button bSearch,bBack;
    String studentID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        bSearch = (Button) findViewById(R.id.bSearch);
        bBack = findViewById(R.id.bBack);
        etStudentID = (EditText) findViewById(R.id.etStudentID);




        bSearch.setOnClickListener(this);
        bBack.setOnClickListener(this);
    }



    @Override

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bSearch:
                startActivity(new Intent(this,showinformation.class));


                break;
            case R.id.bBack:
                startActivity(new Intent(this,Mainfunction.class));


                break;


        }


    }
}
