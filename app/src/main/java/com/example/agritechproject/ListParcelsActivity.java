package com.example.agritechproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.agritechproject.GestionParcelle.UpdateParcelle.UpdateParcelle;
import com.example.agritechproject.GestionParcelle.UpdateParcelle.UpdateParcelleActivity;
import com.example.agritechproject.Models.Parcel;
import com.example.agritechproject.Models.ParcelJournal;
import com.example.agritechproject.Services.ParcelJournalService;
import com.example.agritechproject.Services.ParcelService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ListParcelsActivity extends AppCompatActivity {
    RecyclerView recyclerViewParcels;
    FloatingActionButton btnFlottant;
    private RecyclerView.LayoutManager layoutManager;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_parcels);
        recyclerViewParcels = findViewById(R.id.recyclerview_parcels);
        btnFlottant = findViewById(R.id.btn_show_dialog);
        builder = new AlertDialog.Builder(this);
        getParcelsByIdFerme();

        ImageView imageBack =findViewById(R.id.image_back);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }

        });

    }
    public void alertDialog(int idParcelle){


        //Setting message manually and performing action on button click
        builder.setMessage("Voulez-vous vraiment supprimer cette ferme ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Call<Void>  deleteFarm= ApiClient.getApiClient().create(ParcelService.class).deleteParcelle(idParcelle);
                        deleteFarm.enqueue(new  Callback<Void>(){

                            @Override
                            public void onResponse(Response<Void> response, Retrofit retrofit) {
                                if (response.isSuccess()) {

                                    Toast.makeText(
                                           getApplicationContext(),
                                            "deleted",
                                            Toast.LENGTH_LONG
                                    ).show();
                                } else {
                                    Toast.makeText(
                                            getApplicationContext(),
                                            response.errorBody().toString(),
                                            Toast.LENGTH_LONG
                                    ).show();
                                }
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Log.e("erreur", t.getMessage());
                            }


                        });
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Suppression Ferme");
        alert.show();
    }
    public  void getParcelsByIdFerme(){
        Intent intent = getIntent();
        String id = intent.getStringExtra("idFerme");
        Call<List<ParcelJournal>> getParcelsByIdFarm=ApiClient.getApiClient().create(ParcelJournalService.class).getLastJournalParcelleByIdFerme(id);
        getParcelsByIdFarm.enqueue(new Callback<List<ParcelJournal>>() {
            @Override
            public void onResponse(Response<List<ParcelJournal>> response, Retrofit retrofit) {
                List<ParcelJournal> listParcels;
                if (response.isSuccess()){
                    listParcels=response.body();
                    if (listParcels.size()>0){
                        layoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerViewParcels.setLayoutManager(layoutManager);
                        recyclerViewParcels.setHasFixedSize(true);
                        ParcelJournalAdapter ParcelJournalAdapter = new ParcelJournalAdapter(getApplicationContext(),listParcels);
                        recyclerViewParcels.setAdapter(ParcelJournalAdapter);

                        ParcelJournalAdapter.setOnItemClickListener(new ParcelJournalAdapter.onItemClickListener() {
                            @Override
                            public void onItemClick(Integer position) {


                                int idParcelle=response.body().get(position).getId();
                                String nomParcelle=response.body().get(position).getNom();
                                Float surface=response.body().get(position).getSurface();
                                int totalImplantation= response.body().get(position).getTotalImplantation();
                                Float temperatureIdeale=response.body().get(position).getTemperatureIdeale();
                                Float humiditeIdeale=response.body().get(position).getHumiditeIdeale();
                                Float humiditeSolIdeale=response.body().get(position).getHumiditeSolIdeale();
                                String cultureLibelle = response.body().get(position).getLibelleCulture();
                                int cultureId = response.body().get(position).getIdCulture();
                                UpdateParcelle.idParcelle=idParcelle;
                                UpdateParcelle.nom= nomParcelle;
                                UpdateParcelle.surface=surface;
                                UpdateParcelle.totalImplantation=totalImplantation;
                                UpdateParcelle.temperatureIdeale=temperatureIdeale;
                                UpdateParcelle.humiditeIdeale=humiditeIdeale;
                                UpdateParcelle.humiditeSolIdeale=humiditeSolIdeale;
                                UpdateParcelle.idCulture=cultureId ;
                                UpdateParcelle.libelleCulture=cultureLibelle;


                                Intent intentUpdateParcel = new Intent(getApplicationContext(), UpdateParcelleActivity.class);
                                startActivity(intentUpdateParcel);
                            }

                    });
                        SwipeHelper swipeHelper = new SwipeHelper(getApplicationContext(),recyclerViewParcels,false) {
                            @Override
                            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                                underlayButtons.add(new SwipeHelper.UnderlayButton(
                                        "Delete",
                                        AppCompatResources.getDrawable(
                                                getApplicationContext(),
                                                R.drawable.ic_baseline_delete_white
                                        ),
                                        Color.parseColor("#FB4C0D"),
                                        Color.parseColor("#FFFFFF"),
                                        new SwipeHelper.UnderlayButtonClickListener() {
                                            @Override
                                            public void onClick(int pos) {
                                                int idParcelle= response.body().get(pos).getId();
                                                Log.i("id Ferme",String.valueOf(idParcelle));
                                                alertDialog(idParcelle);

                                            }
                                        }
                                ));

                                underlayButtons.add(new SwipeHelper.UnderlayButton(
                                        "Edit",

                                        AppCompatResources.getDrawable(
                                                getApplicationContext(),
                                                R.drawable.ic_baseline_edit_white
                                        ),
                                        Color.parseColor("#22780F"),
                                        Color.parseColor("#FFFFFF"),
                                        new SwipeHelper.UnderlayButtonClickListener() {
                                            @Override
                                            public void onClick(int pos) {
                                                // TODO: OnUnshare
                                            }
                                        }
                                ));
                            }
                        };
                    }
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"failed"+ t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}