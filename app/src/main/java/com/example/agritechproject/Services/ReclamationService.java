package com.example.agritechproject.Services;

import com.example.agritechproject.Models.Farm;
import com.example.agritechproject.Models.Reclamation;
import com.example.agritechproject.Models.TypeProbleme;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ReclamationService {
    @GET("getReclamationByIdUser/{idUser}")
    Call<List<Reclamation>> getReclamationByIdUser(@Path("idUser") String idUser);

    @POST("addReclamation/{idUser}")
    Call<Reclamation> addReclamation(@Body Reclamation reclamation, @Path("idUser")String idUser);
}
