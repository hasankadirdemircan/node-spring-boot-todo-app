package com.hkdemircan.todoapp_mobile.restapi;

public class BaseManager {
    protected RestApi getRestApiClient(){
        RestApiClient restApiClient = new RestApiClient(BaseUrl.url);
        return restApiClient.getRestApi();
    }
}
