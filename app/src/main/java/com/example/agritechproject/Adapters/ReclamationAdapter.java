package com.example.agritechproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agritechproject.Models.Reclamation;
import com.example.agritechproject.Models.TypeProbleme;
import com.example.agritechproject.R;

import java.util.ArrayList;
import java.util.List;

public class ReclamationAdapter extends RecyclerView.Adapter<ReclamationAdapter.ReclamationViewHolder> {

    List<Reclamation> listTypeReclamations;
    Context context;
    public ReclamationAdapter(Context context, List<Reclamation> listTypeReclamations){
        this.context=context;
        this.listTypeReclamations=listTypeReclamations;
    }
    @NonNull
    @Override
    public ReclamationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reclamation_item,parent,false);
        ReclamationViewHolder reclamationViewHolder= new ReclamationViewHolder(view);
        return  reclamationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReclamationViewHolder holder, int position) {
        Reclamation reclamation= listTypeReclamations.get(position);
        List<TypeProbleme> listTypeProblemes =new ArrayList<>();
        listTypeProblemes=reclamation.getTypeProbleme();
        String nomReclamation = listTypeProblemes.get(0).getNom();
        if (listTypeProblemes.size()>0) {
            for (int i = 0; i < listTypeProblemes.size(); i++){
                nomReclamation+=","+listTypeProblemes.get(i).getNom();
            }


        }
        int range=position+1;
        holder.txtNomReclamation.setText(nomReclamation);
        holder.txtNumReclamation.setText("Reclamation "+range);
        holder.txtEtatReclamation.setText(reclamation.getEtatReclamtion());
        if (reclamation.getEtatReclamtion()=="En cours"){
            holder.txtEtatReclamation.setBackgroundResource(R.drawable.rounded_corner_view_jaune);
        }else if(reclamation.getEtatReclamtion()=="Résolu"){
            holder.txtEtatReclamation.setBackgroundResource(R.drawable.rounded_corner_view_green);

        }else{
            holder.txtEtatReclamation.setBackgroundResource(R.drawable.rounded_corner_view_green);
        }
    }

    @Override
    public int getItemCount() {
        return  listTypeReclamations.size();
    }

    public class  ReclamationViewHolder extends RecyclerView.ViewHolder{
        TextView txtNomReclamation,txtDetailsReclamation,txtNumReclamation,txtEtatReclamation;
        public  ReclamationViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNomReclamation = itemView.findViewById(R.id.txt_nom_reclamation);
            txtDetailsReclamation  = itemView.findViewById(R.id.txt_details_reclamation);
            txtNumReclamation = itemView.findViewById(R.id.txt_num_reclamation);
            txtEtatReclamation = itemView.findViewById(R.id.txt_etat_reclamation);



        }


    }
}
