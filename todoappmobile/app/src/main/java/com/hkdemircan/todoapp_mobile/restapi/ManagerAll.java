package com.hkdemircan.todoapp_mobile.restapi;

import com.hkdemircan.todoapp_mobile.model.UserCreate;

import retrofit2.Call;

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
}
