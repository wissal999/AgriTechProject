package com.example.agritechproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agritechproject.Adapters.TypeProblemeAdapter;
import com.example.agritechproject.Models.Reclamation;
import com.example.agritechproject.Models.TypeProbleme;
import com.example.agritechproject.Services.ReclamationService;
import com.example.agritechproject.Services.TypeProblemeService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ReclamationActivity extends AppCompatActivity {
    RecyclerView recyclerViewTypeProblemes ;
    RecyclerView.LayoutManager layoutManager;
    TypeProblemeAdapter typeProblemeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation);

        getTypeProblemes();
        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        ImageView imageBack =findViewById(R.id.image_back);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }

        });
        Button btnLogin =findViewById(R.id.btn_envoie_reclamation);
        TextView txtDescriptionReclamation =findViewById(R.id.edt_description);

       /* btnLogin.setOnClickListener(View.OnClickListener {



        })*/
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idUser= preferences.getString("idUser", "");
                String email= preferences.getString("email", "");
                String userNumber= preferences.getString("userNumber", "");
                String username= preferences.getString("username", "");
                String description= txtDescriptionReclamation.getText().toString().trim();
                Reclamation reclamation=new Reclamation();
                reclamation.setUsername(username);
                reclamation.setEmail(email);
                reclamation.setPhoneNumber(userNumber);
                reclamation.setDescription(description);
               reclamation.setEtatReclamtion("non résolu");


                ArrayList<TypeProbleme> listTypeProblemechecked =new ArrayList<>();
                for (int i = 0; i < typeProblemeAdapter.getCheckedItem().size(); i++){
                    TypeProbleme typeProbleme=new TypeProbleme();
                    typeProbleme.setId(typeProblemeAdapter.getCheckedItem().get(i).getId());
                    listTypeProblemechecked.add(typeProbleme);
                    Log.i("checked",listTypeProblemechecked.toString());


                }


                reclamation.setTypeProbleme(listTypeProblemechecked);

                Log.i("Reclamation", username+" "+userNumber+" ");
                addReclamation(reclamation, idUser);
            }
        });




        }



    void addReclamation(Reclamation reclamation,String idUser){
        Call<Reclamation> addReclamation =ApiClient.getApiClient().create(ReclamationService.class).addReclamation(reclamation,idUser);
        addReclamation.enqueue(new  Callback<Reclamation>() {
            @Override
            public void onResponse(Response<Reclamation> response, Retrofit retrofit) {
                if (response.isSuccess()) {


                }

                else{
                    Log.e("erreur","erreur");
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("erreur", t.getMessage());
            }


        });
    }
    void getTypeProblemes() {

        Call<List<TypeProbleme>> getParcels=ApiClient.getApiClient().create(TypeProblemeService.class).getAllTypeProblemes();
        getParcels.enqueue(new Callback<List<TypeProbleme>>() {
            @Override
            public void onResponse(Response<List<TypeProbleme>> response, Retrofit retrofit) {
                List<TypeProbleme> listTypeProbs;
                if (response.isSuccess()) {

                    listTypeProbs = response.body();

                    Log.i("listes parcelles",  listTypeProbs.toString());
                    recyclerViewTypeProblemes = findViewById(R.id.recyclerview_type_problemes);
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerViewTypeProblemes.setLayoutManager(layoutManager);
                    typeProblemeAdapter = new TypeProblemeAdapter(getApplicationContext(),listTypeProbs);
                    recyclerViewTypeProblemes.setAdapter(typeProblemeAdapter);
//                    Log.i("wiss", typeProblemeAdapter.getCheckedItem().toString());

                }


            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("erreur", t.getMessage());
            }


            });
    }
}