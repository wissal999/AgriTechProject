package com.example.agritechproject.Services;

import com.example.agritechproject.Models.Farm;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

public interface FarmService {
    @GET("getFermeByIdUser/{idUser}")
    Call<List<Farm>> getFarmsByIdUser(@Path("idUser") String idUser);

    @Headers("content-type: application/json")
    @POST("addFerme/{idFerme}")
    Call<Farm> addFarm(@Body Farm farm, @Path("idFerme")String idFerme);

    @DELETE("deleteFerme/{idFerme}")
    Call<Void> deleteFarm(@Path("idFerme") int idFerme);

}
