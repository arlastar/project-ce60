package com.example.arlastar.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Mainfunction extends AppCompatActivity implements View.OnClickListener {
    Button bCheckinformation, bCheckname,bLogout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainfunction);
        bCheckinformation = (Button) findViewById(R.id.bCheckinformation);
        bCheckname = (Button) findViewById(R.id.bCheckname);
        bLogout = (Button) findViewById(R.id.bLogout);

        bCheckinformation.setOnClickListener(this);
        bCheckname.setOnClickListener(this);
        bLogout.setOnClickListener(this);
    }




    @Override

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bCheckname:
                startActivity(new Intent(this,readnfc.class));


                break;

        }
        switch (view.getId()) {

            case R.id.bCheckinformation:
                startActivity(new Intent(this,search.class));

                break;

        }
        switch (view.getId()) {

            case R.id.bLogout:
                startActivity(new Intent(this,login.class));

                break;

        }

    }
}
