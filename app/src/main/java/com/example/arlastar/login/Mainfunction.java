package com.example.arlastar.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mainfunction extends AppCompatActivity implements View.OnClickListener {
    Button bCheckinformation, bCheckname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainfunction);
        bCheckinformation = (Button) findViewById(R.id.bCheckinformation);
        bCheckname = (Button) findViewById(R.id.bCheckname);

        bCheckinformation.setOnClickListener(this);
        bCheckname.setOnClickListener(this);
    }

    @Override

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bCheckname:
                startActivity(new Intent(this,readnfc.class));


                break;

        }

    }
}
