package com.example.agritechproject;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ApiClient {
    public static final String URL="http://192.168.0.6:8080/";
    public static Retrofit retrofit= null ;
    public static Retrofit getApiClient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
