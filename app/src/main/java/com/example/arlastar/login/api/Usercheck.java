package com.example.arlastar.login.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import okhttp3.ResponseBody;
/**
 * Created by Arlastar on 3/15/2018.
 */

public interface Usercheck {
    @POST("User/")
    Call<LoginResponse> createAccount(@Body User user);


    @POST("search/")
    Call<List<GetResponse>> getDetail2(@Body User2 user2);

    @POST("student/check/")
    Call<GetResponse> getDetail(@Body User user);


    @GET("place/")
    Call<List<placeresponse>> placeresponse();


}

