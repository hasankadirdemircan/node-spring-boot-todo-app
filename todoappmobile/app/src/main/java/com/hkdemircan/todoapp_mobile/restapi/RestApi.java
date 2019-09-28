package com.hkdemircan.todoapp_mobile.restapi;

import com.hkdemircan.todoapp_mobile.model.LoginUser;
import com.hkdemircan.todoapp_mobile.model.TodoCreate;
import com.hkdemircan.todoapp_mobile.model.UserCreate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RestApi {

    /*
   Register
    */
    @POST("/user")
    Call<Void> registerUser(@Body UserCreate userCreate);

    /*
    Login and token take
     */
    @POST("/login")
    Call<Void> loginUser(@Body LoginUser loginUser);

    /*
    save to do
     */
    @POST("/todo")
    Call<Void> saveTodo(@Header("Authorization") String token, @Body TodoCreate todoCreate);
}
