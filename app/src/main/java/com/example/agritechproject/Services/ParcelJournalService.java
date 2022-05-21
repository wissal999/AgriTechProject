package com.example.agritechproject.Services;

import com.example.agritechproject.Models.ParcelJournal;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface ParcelJournalService {
    @GET("getLastData/{idParcelle}")
    Call<ParcelJournal> getLastData(@Path("idParcelle") int idParcelle);



    @GET("getLastJournalParcelleByIdFerme/{idFerme}")
    Call<List<ParcelJournal>> getLastJournalParcelleByIdFerme(@Path("idFerme")String id);
}
