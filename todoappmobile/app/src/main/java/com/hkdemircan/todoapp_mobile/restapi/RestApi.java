package com.hkdemircan.todoapp_mobile.restapi;

import com.hkdemircan.todoapp_mobile.model.UserCreate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestApi {

    /*
   Register
    */
    @POST("/user")
    Call<Void> registerUser(@Body UserCreate userCreate);

}
