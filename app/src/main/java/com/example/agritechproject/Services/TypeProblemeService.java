package com.example.agritechproject.Services;

import com.example.agritechproject.Models.TypeProbleme;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

public interface TypeProblemeService {
    @GET("getTypeProblemes")
    Call<List<TypeProbleme>> getAllTypeProblemes();
}
