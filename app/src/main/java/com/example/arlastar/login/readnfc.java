package com.example.arlastar.login;


import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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


public class readnfc extends AppCompatActivity {

    private NfcAdapter nfcAdapter;
    TextView textViewInfo, textViewTagInfo, textViewBlock,TextViewBlock1, student_idtxt, statuscode, studentname, student_faculty, student_major;
    ImageView imageView,student_firstcheck, student_secondcheck, student_thirdcheck;
    byte[] userID1 = {};

    String userID = new String(userID1, "UTF-8");
    //String userID = "";
    String student_id;
    String studentnamestring = "";
    String faculty;
    String major;
    String url = "http://161.246.35.220:9090/";
    String url1 = "http://161.246.35.220:9090";
    Boolean firstcheck;
    Boolean secondcheck;
    Boolean thirdcheck;
    Button bBack;


   // int checkID = intent.getIntExtra("check", 0);
    int checkID;
    String day="";
    String period="";
    String place="";
   /* Intent intent = getIntent();
    int checkID = intent.getIntExtra("check", 0);*/

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://161.246.35.220:9090/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Usercheck getdetail = retrofit.create(Usercheck.class);
    PendingIntent pendingIntent;

    public readnfc() throws UnsupportedEncodingException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        Intent intent = getIntent();
        String action = intent.getAction();


        //Intent intent = getIntent();
        checkID = intent.getIntExtra("check",0 );
        day = intent.getStringExtra("day");
        period = intent.getStringExtra("period");
        place = intent.getStringExtra("place");
        Log.i("readnfc", "checkID " + String.valueOf(checkID));
        Log.i("readnfc", "day " + String.valueOf(day));
        Log.i("readnfc", "period " + String.valueOf(period));
        Log.i("readnfc", "place " + String.valueOf(place));
        /*day = getIntent().getExtras().getString("day");
        period = getIntent().getExtras().getString("period");
        place = getIntent().getExtras().getString("place");*/
        textViewInfo = (TextView) findViewById(R.id.info);
        //textViewTagInfo = (TextView)findViewById(R.id.taginfo);
        textViewBlock = (TextView) findViewById(R.id.block);
        //statuscode=findViewById(R.id.statuscode);

        studentname = findViewById(R.id.studentname);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        student_idtxt = findViewById(R.id.student_idtxt);
        student_faculty = findViewById(R.id.student_faculty);
        student_major = findViewById(R.id.student_major);
        student_firstcheck = findViewById(R.id.student_firstcheck);
        student_secondcheck = findViewById(R.id.student_secondcheck);
        student_thirdcheck = findViewById(R.id.student_thirdcheck);
        imageView = (ImageView)findViewById(R.id.imageView);
        bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back();
            }
        });

        if (nfcAdapter == null) {
            Toast.makeText(this,
                    "NFC NOT supported on this devices!",
                    Toast.LENGTH_LONG).show();
            finish();
    } else if (!nfcAdapter.isEnabled()) {
        Toast.makeText(this,
                "NFC NOT Enabled!",
                Toast.LENGTH_LONG).show();
        finish();
    }

        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
  /*      if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
//            Toast.makeText(this,
//                    "onResume() - ACTION_TECH_DISCOVERED",
//                    Toast.LENGTH_SHORT).show();

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            if (tag == null) {
                textViewInfo.setText("tag == null");
            } else {
                String tagInfo = tag.toString() + "\n";
                Log.i("readnfc", "tagInfo " + String.valueOf(checkID));
                //Only android.nfc.tech.MifareClassic specified in nfc_tech_filter.xml,
                //so must be MifareClassic
                //if (checkID == 0) {
                    readMifareClassic(tag);
                //}
            }
        } else {
//            Toast.makeText(this,
//                    "onResume() : " + action,
//                    Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    protected void onNewIntent(Intent intent){
        getTagInfo(intent);
    }

    private void getTagInfo(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag == null) {
            textViewInfo.setText("tag == null");
        } else {
            String tagInfo = tag.toString() + "\n";
            Log.i("readnfc", "tagInfo " + String.valueOf(checkID));
            //Only android.nfc.tech.MifareClassic specified in nfc_tech_filter.xml,
            //so must be MifareClassic
            //if (checkID == 0) {
            readMifareClassic(tag);
            //}
        }
        //Start for Camera

    }
    @Override
    public void onBackPressed() {
        Intent intent =new Intent(this,check.class);
        startActivity(intent);
        finish();





    }
    public void Back(){

        Intent intent =new Intent(this,check.class);
        startActivity(intent);
        finish();


    }


    @Override
    protected void onResume() {
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
        super.onPause();
    }

    public void readMifareClassic(Tag tag) {
        MifareClassic mifareClassicTag = MifareClassic.get(tag);


        new ReadMifareClassicTask(mifareClassicTag).execute();

    }

    private class ReadMifareClassicTask extends AsyncTask<Void, String, Void> {

        /*
        MIFARE Classic tags are divided into sectors, and each sector is sub-divided into blocks.
        Block size is always 16 bytes (BLOCK_SIZE). Sector size varies.
        MIFARE Classic 1k are 1024 bytes (SIZE_1K), with 16 sectors each of 4 blocks.
        */

        MifareClassic taskTag;
        int numOfBlock;
        final int FIX_SECTOR_COUNT = 16;
        byte[] myKeyA = {(byte) 0x4B, (byte) 0x4d, (byte) 0x49, (byte) 0x54, (byte) 0x4c, (byte) 0x39};
        boolean success;
        final int numOfSector = 16;
        final int numOfBlockInSector = 4;
        byte[][][] buffer = new byte[numOfSector][numOfBlockInSector][MifareClassic.BLOCK_SIZE];
        private boolean isTaskCancelled = false;

        public void cancelTask(){
            isTaskCancelled = true;
        }

        private boolean isTaskCancelled(){
            return isTaskCancelled;
        }

        ReadMifareClassicTask(MifareClassic tag) {
            taskTag = tag;
            success = false;
        }



        @Override
        protected Void doInBackground(Void... ignoredparams) {
                Log.i("readnfc", "doInBackground");
                try {

                    taskTag.connect();

                    for (int s = 13; s < 14; s++) {
                        if (taskTag.authenticateSectorWithKeyA(s, myKeyA)) {
                            for (int b = 1; b < 2; b++) {
                                int blockIndex = (s * numOfBlockInSector) + b;
                                buffer[s][b] = taskTag.readBlock(blockIndex);
                            }
                        }
                    }

                    success = true;
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (taskTag != null) {
                        try {
                            taskTag.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }




            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            //display block
            userID = "";
            if (success) {

                String stringBlock = "";
                for (int i = 13; i < 14; i++) {
                    stringBlock += i + " :\n";
                    for (int j = 1; j < 2; j++) {
                        for (int k = 0; k < 8; k++) {
                            buffer[i][j][k] = (byte) (buffer[i][j][k] - 48);
                            //TODO change userID name to whatever you want
                            userID += buffer[i][j][k];
                        }
                    }
                }
               // textViewBlock.setText(userID);
                //if(day  != "")&&(period)
                Log.i("readnfc", userID + " " + String.valueOf(checkID) + " " + day + " " + period + " " + place);


                getDetail(userID, checkID,day,period,place);
                //checkID = 0;

            } else {
                textViewBlock.setText("Fail to read Blocks!!!");
                textViewBlock.setBackgroundResource(R.color.red);
            }
        }
    }

    private void getDetail(String inputIDstring, int checkID,String day,String period,String place) {
//        Toast.makeText(this, "getDetail", Toast.LENGTH_SHORT).show();
        if(day==null)
            day="";
        if(period==null)
            period="";
        if(place==null)
            place="";
        User id = new User(inputIDstring, checkID,day,period,place);
        Call<GetResponse> call = getdetail.getDetail(id);
        call.enqueue(new Callback<GetResponse>() {
            @Override
            public void onResponse(Call<GetResponse> call, Response<GetResponse> response) {
                Toast.makeText(readnfc.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                if (response.code() == 200) {
                    student_id = response.body().getStudent_id();
                    student_idtxt.setText(student_id);
                    student_idtxt.setBackgroundResource(R.color.orange2);
                    studentnamestring = response.body().getNameTitle() + response.body().getFirstName() + " " + response.body().getLastName();
                    studentname.setText(studentnamestring);
                    studentname.setBackgroundResource(R.color.orange2);
                    faculty = response.body().getFaculty();
                    student_faculty.setText(faculty);
                    student_faculty.setBackgroundResource(R.color.orange2);
                    major = response.body().getMajor();
                    student_major.setText(major);
                    student_major.setBackgroundResource(R.color.orange2);
                    firstcheck = response.body().getFirstCheck();
                    if (firstcheck) {
                        student_firstcheck.setImageResource(R.drawable.correct2);


                    } else
                        student_firstcheck.setImageResource(R.drawable.wrong);

                    secondcheck = response.body().getSecondCheck();
                    if (secondcheck) {
                        student_secondcheck.setImageResource(R.drawable.correct2);

                    } else
                        student_secondcheck.setImageResource(R.drawable.wrong);

                    thirdcheck = response.body().getThirdCheck();
                    if (thirdcheck) {
                        student_thirdcheck.setImageResource(R.drawable.correct2);

                    } else
                        student_thirdcheck.setImageResource(R.drawable.wrong);
                    url1="http://161.246.35.220:9090";
                    url1 = url1 + response.body().getImage();
                    loadImageFromUrl(url1);
                    textViewBlock.setText("เช็คชื่อสำเร็จ ");
                    textViewBlock.setBackgroundResource(R.color.green);






//                    Toast.makeText(readnfc.this, ""+student_id, Toast.LENGTH_SHORT).show();
                } else if (response.code() == 404) {
                    textViewBlock.setText("ไม่พบข้อมูลกรุณาติดต่อเจ้าหน้าที่" );
                    textViewBlock.setBackgroundResource(R.color.red);

                    Toast.makeText(readnfc.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                }
                else if (response.code() == 400) {
                    textViewBlock.setText("กรุณาติดต่อเจ้าหน้าที่เนื่องจากขาดการซ้อมหรือเข้าซ้อมผิดสถานที่" );
                    textViewBlock.setBackgroundResource(R.color.red);

                    Toast.makeText(readnfc.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetResponse> call, Throwable t) {

            }
        });

    }
    private void loadImageFromUrl (String url1){
        Picasso.with(this).load(url1).placeholder(R.mipmap.ic_launcher)
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



}
