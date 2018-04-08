package com.example.arlastar.login;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import android.widget.Toast;

import com.example.arlastar.login.api.GetResponse;
import com.example.arlastar.login.api.User;
import com.example.arlastar.login.api.Usercheck;
import com.squareup.picasso.Picasso;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class search extends AppCompatActivity implements View.OnClickListener {
    TextView textViewInfo, textViewTagInfo, textViewBlock, student_idtxt, statuscode, studentname, student_faculty, student_major, student_firstcheck, student_secondcheck, student_thirdcheck;
    ImageView imageView;
    String userID = "";
    String IDstudent;
    String studentnamestring = "";
    String faculty;
    String major;
    String url = "http://161.246.35.220:9090/";
    Boolean firstcheck;
    Boolean secondcheck;
    Boolean thirdcheck;
    EditText etStudentID;
    Button bSearch,bBack;
    int check;
   /* Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://161.246.35.220:9090/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Usercheck getdetail = retrofit.create(Usercheck.class);*/




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        bSearch = (Button) findViewById(R.id.bSearch);
        bBack = findViewById(R.id.bBack);
        etStudentID = findViewById(R.id.etStudentID);








        bSearch.setOnClickListener(this);
        bBack.setOnClickListener(this);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,Mainfunction.class));
        finish();





    }


    @Override

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bSearch:
                String IDstudent = etStudentID .getText().toString();
                Intent intent = new Intent(search.this,showinformation.class);
                intent.putExtra("ID", IDstudent);
                if(etStudentID.getText().toString().length()!=8){
                    etStudentID.setError("Please fill in a Student ID in 8 digit");
                }
                else{
                    etStudentID.setError(null);
                    startActivity(intent);
                    finish();


                }





                break;
            case R.id.bBack:
                startActivity(new Intent(this,Mainfunction.class));
                finish();



                break;


        }


    }
}
