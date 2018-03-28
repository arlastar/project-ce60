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

public class showinformation extends AppCompatActivity implements View.OnClickListener {
    TextView textViewInfo, textViewTagInfo,tv, textViewBlock, student_idtxt, statuscode, studentname, student_faculty, student_major, student_firstcheck, student_secondcheck, student_thirdcheck;
    ImageView imageView;
    String userID = "";
    String student_id;
    String studentnamestring = "";
    String faculty;
    String major;
    String url = "http://10.66.7.182:8000";
    Boolean firstcheck;
    Boolean secondcheck;
    Boolean thirdcheck;
    Button bBack;
    String IDstudent;

    int check;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.66.7.182:8000/student/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Usercheck getdetail = retrofit.create(Usercheck.class);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showinformation);
        bBack = findViewById(R.id.bBack);
        textViewBlock = (TextView) findViewById(R.id.block);
        tv =(TextView) findViewById(R.id.inte);
        IDstudent = getIntent().getExtras().getString("ID");

        tv.setText(IDstudent);
        studentname = findViewById(R.id.studentname);
        student_idtxt = findViewById(R.id.student_idtxt);
        student_faculty = findViewById(R.id.student_faculty);
        student_major = findViewById(R.id.student_major);
        student_firstcheck = findViewById(R.id.student_firstcheck);
        student_secondcheck = findViewById(R.id.student_secondcheck);
        student_thirdcheck = findViewById(R.id.student_thirdcheck);
        imageView = (ImageView)findViewById(R.id.imageView);
        check = 0;
        getDetail(IDstudent, check);








        bBack.setOnClickListener(this);
    }
    private void getDetail(String IDstudent, int check) {
//        Toast.makeText(this, "getDetail", Toast.LENGTH_SHORT).show();
        User id = new User(IDstudent, 0);
        Call<GetResponse> call = getdetail.getDetail(id);
        call.enqueue(new Callback<GetResponse>() {
            @Override
            public void onResponse(Call<GetResponse> call, Response<GetResponse> response) {
                Toast.makeText(showinformation.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                if (response.code() == 200) {
                    student_id = response.body().getStudent_id();
                    student_idtxt.setText(student_id);
                    studentnamestring = response.body().getNameTitle() + response.body().getFirstName() + " " + response.body().getLastName();
                    studentname.setText(studentnamestring);
                    faculty = response.body().getFaculty();
                    student_faculty.setText(faculty);
                    major = response.body().getMajor();
                    student_major.setText(major);
                    firstcheck = response.body().getFirstCheck();
                    if (firstcheck) {
                        student_firstcheck.setText("YES");

                    } else
                        student_firstcheck.setText("NO");

                    secondcheck = response.body().getSecondCheck();
                    if (secondcheck) {
                        student_secondcheck.setText("YES");

                    } else
                        student_secondcheck.setText("NO");

                    thirdcheck = response.body().getThirdCheck();
                    if (thirdcheck) {
                        student_thirdcheck.setText("YES");

                    } else
                        student_thirdcheck.setText("NO");
                    url = url + response.body().getImage();
                    loadImageFromUrl(url);





//                    Toast.makeText(readnfc.this, ""+student_id, Toast.LENGTH_SHORT).show();
                } else {
                    textViewBlock.setText("Unsuccessfull ");
                    textViewBlock.setBackgroundResource(R.color.red);

                }
            }

            @Override
            public void onFailure(Call<GetResponse> call, Throwable t) {

            }
        });

    }
    private void loadImageFromUrl (String url){
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView,new com.squareup.picasso.Callback(){
                    @Override
                    public void onSuccess() {
                        //do smth when picture is loaded successfully

                    }

                    @Override
                    public void onError() {
                        //do smth when there is picture loading error
                    }
                });

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.bBack:
                startActivity(new Intent(this,search.class));
                finish();


                break;


        }


    }
}
