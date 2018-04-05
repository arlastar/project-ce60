package com.example.arlastar.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        tv_result = (TextView) findViewById(R.id.tv_result);
        setContentView(R.layout.activity_check);
        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.text_view_selected);
        bOK = (Button) findViewById(R.id.button_ok);
        bOK.setOnClickListener(this);
        bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId){
                    case R.id.radio0:
                        // do operations specific to this selection
                        break;
                    case R.id.radio1:
                        // do operations specific to this selection
                        break;
                    case R.id.radio2:
                        // do operations specific to this selection
                        break;
                }
            }
        });
    }


    public void onClick(View view) {
        int selectedRadioButtonID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(selectedRadioButtonID);
        if (selectedRadioButtonID != -1) {
            switch (view.getId()) {
                case R.id.button_ok:
                    if (selectedRadioButtonID == R.id.radio0) {
                        startActivity(new Intent(this, check1detail.class));
                        finish();
                        break;
                    }
                    else{
                        startActivity(new Intent(this, readnfc.class));
                        finish();
                        break;

                    }
                case R.id.bBack:
                    startActivity(new Intent(this, Mainfunction.class));
                    finish();
                    break;
            }
        }
        else{
            tv_result.setText("Nothing selected from Radio Group.");
        }





    }
}
