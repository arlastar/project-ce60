package com.example.arlastar.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;


public class Mainfunction extends AppCompatActivity implements View.OnClickListener {
    Button bCheckinformation, bCheckname,bLogout;
    DrawerLayout drawerLayout;




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
    boolean doubleBackPress = false;

    @Override
    public void onBackPressed() {
                    if (doubleBackPress)
                        super.onBackPressed();
                    else
                        doubleBackPress = true;
                    final CoordinatorLayout coordinatorLayout = findViewById(R.id.setCoordinateID);
                    Snackbar.make(coordinatorLayout, getString(R.string.pressbackagain), Snackbar.LENGTH_SHORT).show();
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            doubleBackPress = false;
                        }
                    }, 2000);

            }







    @Override

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bCheckname:
                startActivity(new Intent(this,check.class));
                finish();



                break;

        }
        switch (view.getId()) {

            case R.id.bCheckinformation:
                startActivity(new Intent(this,search.class));
                finish();


                break;

        }
        switch (view.getId()) {

            case R.id.bLogout:
                if(view.getId()==R.id.bLogout){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Are you sure you want to logout?");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(Mainfunction.this,login.class);

                            startActivity(intent);
                            finish();

                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }



        }



    }
}
