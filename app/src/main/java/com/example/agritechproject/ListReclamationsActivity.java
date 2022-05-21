package com.example.agritechproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.agritechproject.Adapters.ReclamationAdapter;
import com.example.agritechproject.Models.Reclamation;
import com.example.agritechproject.Services.ReclamationService;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ListReclamationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reclamations);
        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String idUser= preferences.getString("idUser", "");
        getReclamtionByUserId(idUser);
        ImageView imageBack =findViewById(R.id.image_back);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }

        });
    }
    void getReclamtionByUserId(String idUser) {
        Call<List<Reclamation>> getReclamations =ApiClient.getApiClient().create(
                ReclamationService.class).getReclamationByIdUser(idUser);
        getReclamations.enqueue(new Callback<List<Reclamation>>() {
            @Override
            public void onResponse(Response<List<Reclamation>> response, Retrofit retrofit) {
                List<Reclamation> listReclamations;
                if (response.isSuccess()) {
                    listReclamations = response.body();
                    RecyclerView recyclerViewReclamations =findViewById(R.id.recyclerview_reclamations);
                            Log.i("listes parcelles", listReclamations.toString());
                    recyclerViewReclamations.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    ReclamationAdapter reclamationAdapter = new ReclamationAdapter(getApplicationContext(),listReclamations);
                    recyclerViewReclamations.setAdapter(reclamationAdapter);

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }

        });
    }
}