package com.example.arlastar.login;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.arlastar.login.api.Usercheck;
import com.example.arlastar.login.api.placeresponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;



public class check1detail extends AppCompatActivity  {
    Spinner spinner,spinner2,spinner3;
    Button bBack,bOK;
    String URL="http://161.246.35.220:9090/place/";
    ArrayList<String> placeresult;
    ArrayList<String> dayresult;
    ArrayList<String> periodresult;

    List<placeresponse> placeresponses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check1detail);
        Log.i("VOLLEY", "helloooooooo startttttttttttt");

        placeresult=new ArrayList<>();
        dayresult=new ArrayList<>();
        periodresult=new ArrayList<>();
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.getBackground().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner3.getBackground().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back();
            }
        });
        bOK = (Button) findViewById(R.id.button_ok);
        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OK();
            }
        });

        loadSpinnerData();



     /*   ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.date_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.time_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears

        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
        spinner3.setAdapter(adapter3);

        spinner3.setOnItemSelectedListener(this);*/

    }

    private void loadSpinnerData() {
        Log.i("VOLLEY", "startttttttttttt");
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "http://161.246.35.220:9090/place/";
            //final String requestBody = jsonArray.toString();


            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                    new Response.Listener<String>() {
                        public JSONArray jsonArray;

                        @Override
                        public void onResponse(String response) {
                            response=fixEncodingUnicode(response);
                            Log.i("VOLLEY", response);
                            try {

                                jsonArray = new JSONArray(response);
                                for (int i=0;i<jsonArray.length();i++){

                                    JSONObject object = (JSONObject) jsonArray.get(i);
                                    String key = object.getString("key");
                                    if (key.equals("place")) {
                                        JSONArray result = object.getJSONArray("result");
                                        for (int j = 0; j < result.length(); j++) {
                                            String value = (String) result.get(j);
                                            Log.i("VOLLEY", value);
                                            placeresult.add(value);
                                        }
                                    }
                                    if (key.equals("day")) {
                                        JSONArray result = object.getJSONArray("result");
                                        for (int j = 0; j < result.length(); j++) {
                                            String value = (String) result.get(j);
                                            Log.i("VOLLEY", value);
                                            dayresult.add(value);
                                        }
                                    }
                                    if (key.equals("period")) {
                                        JSONArray result = object.getJSONArray("result");
                                        for (int j = 0; j < result.length(); j++) {

                                            String value = (String) result.get(j);
                                            Log.i("VOLLEY", value);
                                            periodresult.add(value);
                                        }
                                    }
                                }
                                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, placeresult);
                                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner.setAdapter(dataAdapter);

                                ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, dayresult);
                                dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner2.setAdapter(dataAdapter1);

                                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, periodresult);
                                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner3.setAdapter(dataAdapter2);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    },new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY", error.toString());
                    }
            })
            {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

            };


            requestQueue.add(stringRequest);
    }
    public static String fixEncodingUnicode(String response) {
        String str = "";
        try {
            str = new String(response.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }

        String decodedStr = Html.fromHtml(str).toString();
        return  decodedStr;
    }




    public void Back(){

        Intent intent =new Intent(this,check.class);
        startActivity(intent);
        finish();


    }

    public void OK(){

        Intent intent =new Intent(this,readnfc.class);
        startActivity(intent);
        finish();


    }


    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(Object response);
    }






   /* @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sSelected=parent.getItemAtPosition(position).toString();
        Toast.makeText(this,sSelected,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/
}