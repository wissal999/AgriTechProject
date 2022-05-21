package com.example.agritechproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.agritechproject.Models.Farm;
import com.example.agritechproject.Models.User;
import com.example.agritechproject.Services.FarmService;
import com.example.agritechproject.Services.UserService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class AddFarmActivity extends AppCompatActivity {
    EditText txtNomFerme,txtLieuFerme;
    Button btnAjout;
    ImageView imageBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_farm);
        txtNomFerme=findViewById(R.id.txt_nom_ferme);
        txtLieuFerme=findViewById(R.id.txt_lieu_ferme);
        btnAjout=findViewById(R.id.btn_ajout);
        imageBack =findViewById(R.id.image_back);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        btnAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFarm();
                onBackPressed();
            }
        });
   
    }

    private void addFarm() {
        String  NomFerme= txtNomFerme.getText().toString().trim();
        String  LieuFerme= txtLieuFerme.getText().toString().trim();

        Farm farm=new Farm();
        farm.setNomFerme(NomFerme);
        farm.setLieuFerme(LieuFerme);
        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String idUser= preferences.getString("idUser", "");
        Call<Farm> addFarm=ApiClient.getApiClient().create(FarmService.class).addFarm(farm,idUser.toString());

        addFarm.enqueue(new Callback<Farm>() {
            @Override
            public void onResponse(Response<Farm> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    Toast.makeText(getApplicationContext(), "farm added", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"failed"+ t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}