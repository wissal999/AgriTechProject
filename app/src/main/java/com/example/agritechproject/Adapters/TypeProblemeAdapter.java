package com.example.agritechproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agritechproject.Models.TypeProbleme;
import com.example.agritechproject.R;

import java.util.ArrayList;
import java.util.List;

public class TypeProblemeAdapter extends RecyclerView.Adapter<TypeProblemeAdapter.TypeProblemeViewHolder> {
    ArrayList<TypeProbleme> CheckedItem = new ArrayList<>();

    public ArrayList<TypeProbleme> getCheckedItem() {
        return CheckedItem;
    }

    List<TypeProbleme> listTypeProblemes;
    Context context;
    public TypeProblemeAdapter(Context context, List<TypeProbleme> listTypeProblemes){
        this.context=context;
        this.listTypeProblemes=listTypeProblemes;
    }

    @NonNull
    @Override
    public TypeProblemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.type_probleme_item,parent,false);
        TypeProblemeViewHolder typeProblemeViewHolder= new TypeProblemeViewHolder(view);
        return  typeProblemeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TypeProblemeViewHolder holder, int position) {

        TypeProbleme typeProbleme = listTypeProblemes.get(position);

        holder.txtIdTypeProb.setText(String.valueOf(typeProbleme.getId()));
        holder.txtNomTypeProb.setText(typeProbleme.getNom());
        holder.checkBox.setChecked(typeProbleme.getChecked());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    typeProbleme.setChecked(true);
                    Toast.makeText(context,"id"+typeProbleme.getId().toString(),Toast.LENGTH_LONG).show();
                    CheckedItem.add(typeProbleme);
                }
                else{
                    typeProbleme.setChecked(false);
                    CheckedItem.remove(typeProbleme);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listTypeProblemes.size();
    }

    public static class TypeProblemeViewHolder extends RecyclerView.ViewHolder{
        TextView txtIdTypeProb,txtNomTypeProb;
        CheckBox checkBox;


        public TypeProblemeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIdTypeProb= itemView.findViewById(R.id.txt_id_type_prob);
            txtNomTypeProb = itemView.findViewById(R.id.txt_nom_type_prob);
            checkBox= itemView.findViewById(R.id.checkbox_type_prob);



        }


    }
}
