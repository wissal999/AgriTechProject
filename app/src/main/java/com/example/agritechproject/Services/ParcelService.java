package com.example.agritechproject.Services;

import com.example.agritechproject.Models.Parcel;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface ParcelService {
    /*@GET("ListnameParcelle/{idFerme}")
    Call<List<String>> getNamesParcels(@Path("idFerme")String idFerme);*/

    @GET("getParcellesByIdFerme/{idFerme}")
    Call<List<Parcel>> getParcelsByIdFerme(@Path("idFerme")int idFerme);

    @PUT("updateEtatArrosageParcelle/{id}/{etatArrosage}")
    Call<Void>updateEtatArrosage(@Path("id") int id, @Path("etatArrosage")  Boolean etatArrosage);


    @PUT("updatetypeArrosageParcelle/{id}/{typeArrosage}")
    Call<Void> updateTypeArrosage(@Path("id") int id, @Path("typeArrosage") Boolean typeArrosage);

    @DELETE("deleteParcelle/{idParcelle}")
    Call<Void> deleteParcelle(@Path("idParcelle") int idParcelle);
    @Headers("content-type: application/json")
    @PUT("updateParcelle/{id}/1")
    Call<Parcel> updateParcelle(@Path("id") int id, @Body Parcel parcel);
}
