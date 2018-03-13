package com.example.arlastar.login;


import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.io.IOException;
import android.widget.Toast;


public class readnfc extends AppCompatActivity {

    private NfcAdapter nfcAdapter;
    TextView textViewInfo, textViewTagInfo, textViewBlock;
    String userID ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        textViewInfo = (TextView)findViewById(R.id.info);
        textViewTagInfo = (TextView)findViewById(R.id.taginfo);
        textViewBlock = (TextView)findViewById(R.id.block);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter == null){
            Toast.makeText(this,
                    "NFC NOT supported on this devices!",
                    Toast.LENGTH_LONG).show();
            finish();
        }else if(!nfcAdapter.isEnabled()){
            Toast.makeText(this,
                    "NFC NOT Enabled!",
                    Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String action = intent.getAction();

        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
            Toast.makeText(this,
                    "onResume() - ACTION_TECH_DISCOVERED",
                    Toast.LENGTH_SHORT).show();

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            if(tag == null){
                textViewInfo.setText("tag == null");
            }else{
                String tagInfo = tag.toString() + "\n";



                //Only android.nfc.tech.MifareClassic specified in nfc_tech_filter.xml,
                //so must be MifareClassic
                readMifareClassic(tag);
            }
        }else{
            Toast.makeText(this,
                    "onResume() : " + action,
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void readMifareClassic(Tag tag){
        MifareClassic mifareClassicTag = MifareClassic.get(tag);



        new ReadMifareClassicTask(mifareClassicTag).execute();

    }

    private class ReadMifareClassicTask extends AsyncTask<Void, Void, Void> {

        /*
        MIFARE Classic tags are divided into sectors, and each sector is sub-divided into blocks.
        Block size is always 16 bytes (BLOCK_SIZE). Sector size varies.
        MIFARE Classic 1k are 1024 bytes (SIZE_1K), with 16 sectors each of 4 blocks.
        */

        MifareClassic taskTag;
        int numOfBlock;
        final int FIX_SECTOR_COUNT = 16;
        byte[] myKeyA = {(byte)0x4B, (byte)0x4d, (byte)0x49, (byte)0x54, (byte)0x4c, (byte)0x39};
        boolean success;
        final int numOfSector = 16;
        final int numOfBlockInSector = 4;
        byte[][][] buffer = new byte[numOfSector][numOfBlockInSector][MifareClassic.BLOCK_SIZE];


        ReadMifareClassicTask(MifareClassic tag){
            taskTag = tag;
            success = false;
        }

        @Override
        protected void onPreExecute() {
            textViewBlock.setText("Reading Tag, don't remove it!");
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                taskTag.connect();

                for(int s=13; s<14; s++){
                    if(taskTag.authenticateSectorWithKeyA(s, myKeyA)) {
                        for(int b=1; b<2; b++){
                            int blockIndex = (s * numOfBlockInSector) + b;
                            buffer[s][b] = taskTag.readBlock(blockIndex);
                        }
                    }
                }

                success = true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                if(taskTag!=null){
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
            //  byte[][] kuy = new byte[0][];
            //display block
            if(success){

                String stringBlock = "";
                for(int i=13; i<14; i++){
                    stringBlock += i + " :\n";
                    for(int j=1; j<2; j++){
                        for(int k=0; k<8; k++){
                            buffer[i][j][k]=(byte)(buffer[i][j][k]-48);
                            //TODO change userID name to whatever you want
                            userID +=buffer[i][j][k];
                        }
                    }
                }
                textViewBlock.setText(userID);

            }else{
                textViewBlock.setText("Fail to read Blocks!!!");
            }
        }
    }

}
