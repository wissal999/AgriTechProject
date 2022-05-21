package com.example.agritechproject.Services;

import com.example.agritechproject.Models.User;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

public interface UserService {
    @Headers("content-type: application/json")
    @POST("authentification")
    Call<User> loginUser(@Body User user);

    @Headers("content-type: application/json")
    @POST("add-user")
    Call<User> addUser(@Body User user);

}
