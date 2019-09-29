package com.hkdemircan.todoapp_mobile.restapi;

import com.hkdemircan.todoapp_mobile.model.GetOneTodo;
import com.hkdemircan.todoapp_mobile.model.GetTodo;
import com.hkdemircan.todoapp_mobile.model.LoginUser;
import com.hkdemircan.todoapp_mobile.model.TodoCreate;
import com.hkdemircan.todoapp_mobile.model.UserCreate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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

    /*
    Get Todos
     */
    @GET("/todo")
    Call<GetTodo> getTodo(@Header("Authorization") String token);

    /*
    Get To do for id
    */
    @GET("/todo/{id}")
    Call<GetOneTodo> getTodoForId(@Header("Authorization") String token, @Path("id") int id);

    /*
    update Todo
     */
    @PUT("/todo/{id}")
    Call<TodoCreate> updateTodo(@Header("Authorization") String token, @Body TodoCreate todoCreate, @Path("id") int id);

    @DELETE("/todo/{id}")
    Call<Void> deleteTodo(@Header("Authorization") String token, @Path("id") int id);
}
