package com.hkdemircan.todoapp_mobile.restapi;

import com.hkdemircan.todoapp_mobile.model.GetTodo;
import com.hkdemircan.todoapp_mobile.model.LoginUser;
import com.hkdemircan.todoapp_mobile.model.TodoCreate;
import com.hkdemircan.todoapp_mobile.model.UserCreate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;

public class ManagerAll extends BaseManager{
    private static ManagerAll ourInstance  = new ManagerAll();

    public static synchronized ManagerAll getInstance(){
        return  ourInstance;
    }

    /*
    Register
     */
    public Call<Void> registerUser(UserCreate userCreate){
        Call call = getRestApiClient().registerUser(userCreate);
        return call;
    }

    /*
    Login and token take
     */
    public Call<Void> loginUser(LoginUser loginUser){
        Call call = getRestApiClient().loginUser(loginUser);
        return call;
    }

    /*
    Save to do
     */
    public Call<Void> saveTodo(String token, TodoCreate todoCreate){
        Call call = getRestApiClient().saveTodo(token, todoCreate);
        return call;
    }

    /*
    Get to do
     */
    public Call<GetTodo> getTodo(String token){
        Call call = getRestApiClient().getTodo(token);
        return call;
    }
}
