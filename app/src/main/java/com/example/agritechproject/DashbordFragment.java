package com.example.agritechproject;

import static java.util.Collections.emptyList;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agritechproject.Models.Farm;
import com.example.agritechproject.Models.Parcel;
import com.example.agritechproject.Models.ParcelJournal;
import com.example.agritechproject.Services.FarmService;
import com.example.agritechproject.Services.ParcelJournalService;
import com.example.agritechproject.Services.ParcelService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class DashbordFragment extends Fragment {

    ProgressBar circularProgressBarTemp,circularProgressBarHum,circularProgressBarHumSol;
    TextView txtPourcentageTemp,txtPourcentageHum,txtPourcentageHumSol;
    AutoCompleteTextView autoCompTxt,txtNameFarm;

    String selectedNameFarm;
    int selectedIdFarm;
    String selectedNameParcel ;
    int selectedIdParcel;
    ConstraintLayout Container;
    SwitchCompat switchEtatArrosage;
    SwitchCompat switchAutoManuelle;
    ArrayAdapter<String> arrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_dashbord, container, false);
        Container=v.findViewById(R.id.container_values);
        circularProgressBarTemp =v.findViewById(R.id.circular_progresbar_temp);
        circularProgressBarHum =v.findViewById(R.id.circular_progresbar_hum);
        circularProgressBarHumSol =v.findViewById(R.id.circular_progresbar_hum_sol);
        txtPourcentageTemp =v.findViewById(R.id.txt_pourcentage_temp);
        txtPourcentageHum =v.findViewById(R.id.txt_pourcentage_hum);
        txtPourcentageHumSol =v.findViewById(R.id.txt_pourcentage_hum_sol);
        autoCompTxt=v.findViewById(R.id.auto_comp_txt);
        txtNameFarm=v.findViewById(R.id.txt_name_farm);
        switchEtatArrosage=v.findViewById(R.id.switchCompat);
        switchEtatArrosage.setEnabled(false);
        switchAutoManuelle=v.findViewById(R.id.switch_auto);

        switchAutoManuelle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    updateTypeArrosage(selectedIdParcel,true);
                    switchEtatArrosage.setEnabled(true);
                }
                else{
                    updateTypeArrosage(selectedIdParcel,false);
                    switchEtatArrosage.setEnabled(false);
                    updateEtat(selectedIdParcel,false);

                }
            }
        });
        switchEtatArrosage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    updateEtat(selectedIdParcel,true);

                }
                else{
                    updateEtat(selectedIdParcel,false);

                }
            }
        });

        climat(selectedIdParcel);
        SharedPreferences preferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String idUser= preferences.getString("idUser", "");
        getNameFarms(idUser);
        return v;
    }

    void updateTypeArrosage(int selectedId,Boolean typeArrosage){
        Call<Void>  updateTypeArrosage= ApiClient.getApiClient().create(ParcelService.class).updateTypeArrosage(selectedId,typeArrosage);
        updateTypeArrosage.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {

            }


        });
    }


    void updateEtat(int selectedId,Boolean etat){
        Call<Void>  getClimat = ApiClient.getApiClient().create(ParcelService.class).updateEtatArrosage(selectedId,etat);
        getClimat.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {

            }


        });
    }
    public void refresh(int seconde){
        Handler handler=new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                climat(selectedIdParcel);
            }
        };
        handler.postDelayed(runnable,seconde);
    }
    void climat(int selectedItem) {

        Call<ParcelJournal> getClimat= ApiClient.getApiClient().create(ParcelJournalService.class).getLastData(selectedItem);
        getClimat.enqueue(new Callback<ParcelJournal>() {
            @Override
            public void onResponse(Response<ParcelJournal> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (response.body() != null) {
                        ParcelJournal responseBody=response.body();
                        if ( responseBody.getTemperature()!=null){
                            txtPourcentageTemp.setText(String.valueOf(responseBody.getTemperature()));
                            circularProgressBarTemp.setProgress(Math.round(responseBody.getTemperature()));
                        }
                        else{
                            txtPourcentageTemp.setText("N.D");
                            circularProgressBarTemp.setProgress(0);

                        }
                        if ( responseBody.getHumidite()!=null) {
                            txtPourcentageHum.setText( String.valueOf(responseBody.getHumidite()));
                            circularProgressBarHum.setProgress(Math.round(responseBody.getHumidite()));
                        }else
                        {
                            txtPourcentageHum.setText("N.D");
                            circularProgressBarHum.setProgress(0);

                        }
                        if ( responseBody.getHumidite_sol()!=null) {
                            txtPourcentageHumSol.setText( String.valueOf(responseBody.getHumidite_sol()));
                            circularProgressBarHumSol.setProgress(Math.round(responseBody.getHumidite_sol()));
                        }
                        else{
                            txtPourcentageHumSol.setText("N.D");
                            circularProgressBarHumSol.setProgress(0);
                        }
                        selectedIdParcel = response.body().getId();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }

    });
        refresh(30);
    }

    void getNamesParcels(int id ) {
        Call<List<Parcel>> getNamesParcels =ApiClient.getApiClient().create(ParcelService.class). getParcelsByIdFerme(id);
        getNamesParcels.enqueue(new Callback<List<Parcel>>() {
            @Override
            public void onResponse(Response<List<Parcel>> response, Retrofit retrofit) {
                List<String> listNamesParcels = new ArrayList<String>();
                List<Integer> listIdParcels= new ArrayList<Integer>();
                //List<String> ListNamesParcels;
                if (response.isSuccess()) {
            if (response.body().size()>0){
                for (int i = 0; i < response.body().size(); i++){

                    listNamesParcels.add(response.body().get(i).getNom());
                    listIdParcels.add(response.body().get(i).getId());
                    Log.d("TAG", "onResponse: size "+listNamesParcels.size());

                    if (listNamesParcels.size()!=0) {
                        Container.setVisibility(View.VISIBLE);
                        Log.i("list names parcels", String.valueOf(listNamesParcels));
                        autoCompTxt.setText(listNamesParcels.get(0));
                        selectedNameParcel = listNamesParcels.get(0);
                        selectedIdParcel=listIdParcels.get(0);
                        arrayAdapter = new ArrayAdapter<String>(
                                requireContext(),
                                R.layout.dropdown_item,
                                listNamesParcels
                        );
                        autoCompTxt.setAdapter(arrayAdapter);

                        List<String> finalListNamesParcels = listNamesParcels;
                        autoCompTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                selectedNameParcel= finalListNamesParcels.get(i);



                            }
                        }) ;

                    }


    }
}else {
    Log.d("TAG", "onResponse: vide");
    Container.setVisibility(View.INVISIBLE);
    autoCompTxt.setText("");
    selectedNameParcel = null;
    //ListNamesParcels= emptyList();
    arrayAdapter = new ArrayAdapter<String>(
            requireContext(),
            R.layout.dropdown_item,
            listNamesParcels
    );


    autoCompTxt.setAdapter(arrayAdapter);
}
                       }
                }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
    void getNameFarms(String id ) {
        Call<List<Farm>> getNamesParcels =ApiClient.getApiClient().create(FarmService.class).getFarmsByIdUser(id);
        getNamesParcels.enqueue(new Callback<List<Farm>>() {
            @Override
            public void onResponse(Response<List<Farm>> response, Retrofit retrofit) {
                List<String> ListNamesFarms = new ArrayList<String>();
                List<Integer> listIdFarms= new ArrayList<Integer>();

                if (response.isSuccess()) {
                    if (response.body() != null) {
                        for (int i = 0; i < response.body().size(); i++){

                            ListNamesFarms.add(response.body().get(i).nomFerme);
                            listIdFarms.add(response.body().get(i).idFerme);
                            Log.i("id Farm", String.valueOf(listIdFarms));
                            if (ListNamesFarms.size()>0) {
                                txtNameFarm.setText(ListNamesFarms.get(0));
                                selectedNameFarm = ListNamesFarms.get(0);
                                selectedIdFarm= listIdFarms.get(0);
                                    getNamesParcels(selectedIdFarm);


                                arrayAdapter = new ArrayAdapter<String>(
                                        requireContext(),
                                        R.layout.dropdown_item,
                                        ListNamesFarms
                                );
                                txtNameFarm.setAdapter(arrayAdapter);
                                txtNameFarm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                        selectedNameFarm= ListNamesFarms.get(i);
                                        selectedIdFarm=listIdFarms.get(i);
                                        Log.i("id Farm",String.valueOf( selectedIdFarm));
                                        getNamesParcels(selectedIdFarm);
                                        //autoCompTxt.setText(String.valueOf(listIdFarms.get(i)));
                                        //selectedNameParcel =String.valueOf(selectedIdParcel);


                                    }
                                }) ;

                            }
                        }
                    }else{

                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
}