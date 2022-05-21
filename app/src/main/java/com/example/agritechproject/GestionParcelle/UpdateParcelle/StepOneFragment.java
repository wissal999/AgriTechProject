package com.example.agritechproject.GestionParcelle.UpdateParcelle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.agritechproject.R;


public class StepOneFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_step_one, container, false);
        EditText edtNameParcel =v.findViewById(R.id.edt_name_parcel_update);
        EditText edtSurface =v.findViewById(R.id.edt_surface_update);
        EditText edtTotalImp =v.findViewById(R.id.edt_total_imp_update);
        AutoCompleteTextView autoCompList =v.findViewById(R.id.auto_comp_liste_culture);
        edtNameParcel.setText(UpdateParcelle.nom);
        edtSurface.setText(UpdateParcelle.surface.toString());
        edtTotalImp.setText(UpdateParcelle.totalImplantation.toString());
        autoCompList.setText(UpdateParcelle.libelleCulture);
        
        edtNameParcel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String name = edtNameParcel.getText().toString().trim();
                UpdateParcelle.nom=name;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtSurface.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Float surface = Float.valueOf(edtSurface.getText().toString().trim());
                UpdateParcelle.surface=surface;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtTotalImp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int totalImp = Integer.parseInt(edtTotalImp.getText().toString().trim());
                UpdateParcelle.totalImplantation=totalImp;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        autoCompList.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String libelleCulture = autoCompList.getText().toString().trim();
                UpdateParcelle.libelleCulture=libelleCulture;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return v;
    }
}