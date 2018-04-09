package com.example.arlastar.login;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import android.widget.Toast;

import com.example.arlastar.login.api.GetResponse;
import com.example.arlastar.login.api.User;
import com.example.arlastar.login.api.User2;
import com.example.arlastar.login.api.Usercheck;
import com.squareup.picasso.Picasso;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class showinformation extends AppCompatActivity implements View.OnClickListener {
    TextView textViewInfo, textViewTagInfo,tv, textViewBlock, student_idtxt, statuscode, studentname, student_faculty, student_major,student_order,student_place;
    ImageView imageView,student_firstcheck, student_secondcheck, student_thirdcheck;

    String userID = "";
    String student_id;
    String studentnamestring = "";
    String studentplace = "";
    String faculty;
    String faculty2;
    int order;
    String major;
    String degree;
    String url = "http://161.246.35.220:9090";
    Boolean firstcheck;
    Boolean secondcheck;
    Boolean thirdcheck;
    Button bBack;
    String IDstudent;
    String first_check,second_check,third_check;


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://161.246.35.220:9090/student/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Usercheck getdetail2 = retrofit.create(Usercheck.class);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showinformation);
        bBack = findViewById(R.id.bBack);
        textViewBlock = (TextView) findViewById(R.id.block);
        IDstudent = getIntent().getExtras().getString("ID");
        studentname = findViewById(R.id.studentname);
        student_place = findViewById(R.id.student_place);
        student_idtxt = findViewById(R.id.student_idtxt);
        student_faculty = findViewById(R.id.student_faculty);
        student_order = findViewById(R.id.student_order);
        student_major = findViewById(R.id.student_major);
        student_firstcheck = findViewById(R.id.student_firstcheck);
        student_secondcheck = findViewById(R.id.student_secondcheck);
        student_thirdcheck = findViewById(R.id.student_thirdcheck);
        imageView = (ImageView)findViewById(R.id.imageView);
        first_check = "3";
        second_check = "3";
        third_check = "3";
        getDetail2(IDstudent,degree,faculty2,major,first_check,second_check,third_check);
        bBack.setOnClickListener(this);
    }
    @Override
    public void onBackPressed() {
                startActivity(new Intent(this,search.class));
                finish();





    }



    private void getDetail2(String IDstudent,String degree,String faculty2,String major, String first_check,String second_check,String third_check) {
//        Toast.makeText(this, "getDetail2", Toast.LENGTH_SHORT).show();
        User2 id = new User2(IDstudent,"3","3","3", "3","3","3");
        Call<List<GetResponse>> call = getdetail2.getDetail2(id);
        call.enqueue(new Callback<List<GetResponse>>() {
            @Override
            public void onResponse(Call<List<GetResponse>> call, Response<List<GetResponse>> response) {
                Toast.makeText(showinformation.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                Log.e("Error response code",""+response.code());
                if (response.code() == 200) {

                    student_id = response.body().get(0).getStudent_id();
                    student_idtxt.setText(student_id);
                    textViewBlock.setBackgroundResource(R.color.orange2);
                    studentnamestring = response.body().get(0).getNameTitle() + response.body().get(0).getFirstName() + " " + response.body().get(0).getLastName();
                    studentname.setText(studentnamestring);
                    order = response.body().get(0).getOrder();
                    student_order.setText(Integer.toString(order));
                    studentplace = response.body().get(0).getPeriod() +  " " + response.body().get(0).getPlace();
                    student_place.setText(studentplace);
                    faculty = response.body().get(0).getFaculty();
                    student_faculty.setText(faculty);


                    firstcheck = response.body().get(0).getFirstCheck();
                    if (firstcheck) {
                        student_firstcheck.setImageResource(R.drawable.correct2);


                    } else
                        student_firstcheck.setImageResource(R.drawable.wrong);

                    secondcheck = response.body().get(0).getSecondCheck();
                    if (secondcheck) {
                        student_secondcheck.setImageResource(R.drawable.correct2);

                    } else
                        student_secondcheck.setImageResource(R.drawable.wrong);

                    thirdcheck = response.body().get(0).getThirdCheck();
                    if (thirdcheck) {
                        student_thirdcheck.setImageResource(R.drawable.correct2);

                    } else
                        student_thirdcheck.setImageResource(R.drawable.wrong);
                    url = url + response.body().get(0).getImage();
                    loadImageFromUrl(url);





//                    Toast.makeText(readnfc.this, ""+student_id, Toast.LENGTH_SHORT).show();
                } else {
                    textViewBlock.setText("Unsuccessfull ");
                    textViewBlock.setBackgroundResource(R.color.red);

                }
            }

            @Override
            public void onFailure(Call<List<GetResponse>> call, Throwable t) {
                Log.e("Error responsecode",""+t.getMessage());
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
