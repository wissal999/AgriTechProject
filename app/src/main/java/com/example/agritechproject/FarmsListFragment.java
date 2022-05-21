package com.example.agritechproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.agritechproject.Models.Farm;
import com.example.agritechproject.Services.FarmService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class FarmsListFragment extends Fragment {
    RecyclerView recyclerViewFarms;
    FloatingActionButton btnFlottant;
    AlertDialog.Builder builder;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_farms_list, container, false);
        recyclerViewFarms = v.findViewById(R.id.recyclerview_farms);
        btnFlottant = v.findViewById(R.id.btn_flottant_ajout);
        builder = new AlertDialog.Builder(getActivity());
        getFarmsByIdUser();
        btnFlottant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getContext(),AddFarmActivity.class);
                startActivity(intent);

            }
        });
        return v;

    }

    public void alertDialog(int idFerme){


        //Setting message manually and performing action on button click
        builder.setMessage("Voulez-vous vraiment supprimer cette ferme ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Call<Void>  deleteFarm= ApiClient.getApiClient().create(FarmService.class).deleteFarm(idFerme);
                        deleteFarm.enqueue(new  Callback<Void>(){

                            @Override
                            public void onResponse(Response<Void> response, Retrofit retrofit) {
                                if (response.isSuccess()) {
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_placeholder, new FarmsListFragment()).commit();
                                    Toast.makeText(
                                            getActivity(),
                                            "deleted",
                                            Toast.LENGTH_LONG
                                        ).show();
                                } else {
                                    Toast.makeText(
                                            getActivity(),
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
                        Toast.makeText(getActivity(),"you choose no action for alertbox",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Suppression Ferme");
        alert.show();
    }
    public  void getFarmsByIdUser(){
        SharedPreferences preferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String idUser= preferences.getString("idUser", "");
        Call<List<Farm>> getFarmsByUserId=ApiClient.getApiClient().create(FarmService.class).getFarmsByIdUser(idUser);
        getFarmsByUserId.enqueue(new Callback<List<Farm>>() {
            @Override
            public void onResponse(Response<List<Farm>> response, Retrofit retrofit) {
                List<Farm> listFarms;
                if (response.isSuccess()){
                    listFarms=response.body();
                    if (listFarms.size()>0){
                        layoutManager = new LinearLayoutManager(getActivity());
                        recyclerViewFarms.setLayoutManager(layoutManager);
                        recyclerViewFarms.setHasFixedSize(true);
                        FarmAdapter farmAdapter = new FarmAdapter(getActivity(),listFarms);
                        recyclerViewFarms.setAdapter(farmAdapter);
                        farmAdapter.setOnItemClickListener(new FarmAdapter.onItemClickListener() {
                            @Override
                            public void onItemClick(Integer position) {
                                int itemFarm= response.body().get(position).idFerme;
                                Log.i("idFerme", String.valueOf(itemFarm));
                                Intent intentFarmParcel = new Intent(getActivity(), ListParcelsActivity.class);
                                intentFarmParcel.putExtra("idFerme",String.valueOf(itemFarm));
                                startActivity(intentFarmParcel);

                            }
                        });
                        SwipeHelper swipeHelper = new SwipeHelper(getActivity(),recyclerViewFarms,false) {
                            @Override
                            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                                underlayButtons.add(new SwipeHelper.UnderlayButton(
                                        "Delete",
                                        AppCompatResources.getDrawable(
                                                getActivity(),
                                                 R.drawable.ic_baseline_delete_white
                                     ),
                                        Color.parseColor("#FB4C0D"),
                                        Color.parseColor("#FFFFFF"),
                                        new SwipeHelper.UnderlayButtonClickListener() {
                                            @Override
                                            public void onClick(int pos) {
                                                int idFerme= response.body().get(pos).idFerme;
                                                Log.i("id Ferme",String.valueOf(idFerme));
                                                alertDialog(idFerme);

                                                //farmAdapter.notifyItemChanged(pos);
                                                farmAdapter.notifyItemRemoved(pos);
                                                farmAdapter.notifyDataSetChanged();
                                                recyclerViewFarms.setAdapter(farmAdapter);
                                            }
                                        }
                                ));

                                underlayButtons.add(new SwipeHelper.UnderlayButton(
                                        "Edit",

                                        AppCompatResources.getDrawable(
                                                getActivity(),
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

            }
        });

    }
}