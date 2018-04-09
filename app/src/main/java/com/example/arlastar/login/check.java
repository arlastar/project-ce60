package com.example.arlastar.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class check extends AppCompatActivity implements View.OnClickListener {
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView,tv_result;
    Button bOK,bBack;
    int checkID;
    String day;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        tv_result = (TextView) findViewById(R.id.tv_result);
        setContentView(R.layout.activity_check);
        Log.i("check", "onCreate");
        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.text_view_selected);
        bOK = (Button) findViewById(R.id.button_ok);
        bOK.setOnClickListener(this);
        bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(this);


    }


    public void onClick(View view) {
        int selectedRadioButtonID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(selectedRadioButtonID);
        Log.i("check", String.valueOf(view.getId()));
            switch (view.getId()) {

                case R.id.button_ok:
                    Log.i("check", String.valueOf(radioGroup.getCheckedRadioButtonId()));
                    if (radioGroup.getCheckedRadioButtonId() == R.id.radio0) {
                        //startActivity(new Intent(this, check1detail.class));
                        int checkID = 1;
                        Intent intent = new Intent(check.this,check1detail.class);
                        intent.putExtra("check", 1);
                        startActivity(intent);
                        finish();
                        break;
                    }
                    else if(selectedRadioButtonID == R.id.radio1){
                        int checkID = 2;
                        Intent intent = new Intent(check.this,readnfc.class);
                        intent.putExtra("check", 2);
                       /* String day ="0";
                        intent.putExtra("day", day);
                        String period ="0";
                        intent.putExtra("period", period);
                        String place ="0";
                        intent.putExtra("place", place);*/
                        startActivity(intent);

                        finish();
                        break;

                    }
                    else {
                        int checkID = 3;
                        Intent intent = new Intent(check.this,readnfc.class);
                        intent.putExtra("check", 3);
                       /* String day ="0";
                        intent.putExtra("day", day);
                        String period ="0";
                        intent.putExtra("period", period);
                        String place ="0";
                        intent.putExtra("place", place);*/
                        check.this.startActivity(intent);

                        finish();
                        break;
                    }
                case R.id.bBack:
                    startActivity(new Intent(this, Mainfunction.class));
                    finish();
                    break;
            }
        }






    }

