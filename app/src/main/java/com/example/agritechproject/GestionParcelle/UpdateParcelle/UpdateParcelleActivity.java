package com.example.agritechproject.GestionParcelle.UpdateParcelle;

import static com.google.android.gms.common.util.CollectionUtils.listOf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.agritechproject.Adapters.ViewPagerAdapter;
import com.example.agritechproject.ApiClient;
import com.example.agritechproject.Models.Farm;
import com.example.agritechproject.Models.Parcel;
import com.example.agritechproject.R;
import com.example.agritechproject.Services.FarmService;
import com.example.agritechproject.Services.ParcelService;
import com.shuhart.stepview.StepView;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class UpdateParcelleActivity extends AppCompatActivity {
    StepView stepView;
    ViewPager2 viewPager;
    Button nextButton,backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_parcelle);
        stepView=findViewById(R.id.stepView);
        viewPager=findViewById(R.id.viewPager);
        nextButton=findViewById(R.id.nextButton);
        backButton=findViewById(R.id.backButton);
        setupStepView();
        setupViewPager();
        setupButtons();
    }
    private void setupStepView(){
        stepView.getState()
                // You should specify only stepsNumber or steps array of strings.
                // In case you specify both steps array is chosen.
                .steps(listOf("First Step", "Second Step", "Third Step")) // You should specify only steps number or steps array of strings.
                // In case you specify both steps array is chosen.
                .stepsNumber(3)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .commit();

        stepView.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {

            }
        });

    }
    private void setupViewPager(){
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),getLifecycle()));

        viewPager.registerOnPageChangeCallback(
                new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        stepView.go(position, true);
                        setButtons(position);
                    }
                }
        );

    }
    private void setupButtons(){

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1, false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1, false);
            }
        });

    }
    private void setButtons(int position){
        if(position==0){

            backButton.setVisibility(View.INVISIBLE);
            nextButton.setVisibility(View.VISIBLE);
        }
        else if (position==1) {

            backButton.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.VISIBLE);
        }
        else {
                backButton.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.VISIBLE);
                nextButton.setText("Valider");
                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updateParcelle();
                    }
                });
              }


    }
    private void updateParcelle() {


        Parcel parcel=new Parcel();
        parcel.setNom(UpdateParcelle.nom);
        parcel.setSurface(UpdateParcelle.surface);
        parcel.setTotalImplantation(UpdateParcelle.totalImplantation);
        parcel.setTemperatureIdeale(UpdateParcelle.temperatureIdeale);
        parcel.setHumiditeIdeale(UpdateParcelle.humiditeIdeale);
        parcel.setHumiditeSolIdeale(UpdateParcelle.humiditeSolIdeale);
        Call<Parcel> updateParcelle= ApiClient.getApiClient().create(ParcelService.class).updateParcelle(UpdateParcelle.idParcelle,parcel);

        updateParcelle.enqueue(new Callback<Parcel>() {
            @Override
            public void onResponse(Response<Parcel> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"failed"+ t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }



}