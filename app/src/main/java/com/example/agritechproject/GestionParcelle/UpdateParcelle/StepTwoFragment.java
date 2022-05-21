package com.example.agritechproject.GestionParcelle.UpdateParcelle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.agritechproject.R;

public class StepTwoFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_step_two, container, false);
        EditText edtTempIdeale =v.findViewById(R.id.edt_temp_ideale);
        EditText edtHumIdeale =v.findViewById(R.id.edt_hum_ideale);
        EditText edtHumSolIdeale =v.findViewById(R.id.edt_hum_sol_ideale);
        edtTempIdeale.setText(UpdateParcelle.temperatureIdeale.toString());
        edtHumIdeale.setText(UpdateParcelle.humiditeIdeale.toString());
        edtHumSolIdeale.setText(UpdateParcelle.humiditeSolIdeale.toString());

        return v;
    }
}