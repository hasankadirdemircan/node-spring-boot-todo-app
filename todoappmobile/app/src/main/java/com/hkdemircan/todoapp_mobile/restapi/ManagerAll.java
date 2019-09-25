package com.hkdemircan.todoapp_mobile.restapi;

public class ManagerAll extends BaseManager{
    private static ManagerAll ourInstance  = new ManagerAll();

    public static synchronized ManagerAll getInstance(){
        return  ourInstance;
    }
}
