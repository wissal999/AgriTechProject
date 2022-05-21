package com.example.agritechproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agritechproject.Models.Farm;

import java.util.List;

public class FarmAdapter extends RecyclerView.Adapter<FarmAdapter.FarmViewHolder> {
    public onItemClickListener mListener;
    interface  onItemClickListener{
        void onItemClick(Integer position);
    }
    public void setOnItemClickListener(onItemClickListener listener){
        mListener=listener;
    }

    List<Farm> listFarms;
    Context context;
    public FarmAdapter(Context context, List<Farm>listFarms){
        this.context=context;
        this.listFarms=listFarms;
    }
    @NonNull
    @Override
    public FarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.farm_item,parent,false);
        FarmViewHolder farmViewHolder= new FarmViewHolder(view,mListener);
        return  farmViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FarmViewHolder holder, int position) {
        final Farm farm = listFarms.get(position);
        holder.txtidFarm.setText(farm.idFerme.toString());
        holder.txtnameFarm.setText(farm.nomFerme);
        holder.txtLieu.setText(farm.lieuFerme);

    }

    @Override
    public int getItemCount() {
        return listFarms.size();
    }

    public static class FarmViewHolder extends RecyclerView.ViewHolder{
         TextView txtnameFarm,txtLieu,txtidFarm;


        public FarmViewHolder(@NonNull View itemView,onItemClickListener listener) {
            super(itemView);
            txtidFarm = itemView.findViewById(R.id.txt_id_ferme);
            txtnameFarm = itemView.findViewById(R.id.txt_name_ferme);
            txtLieu= itemView.findViewById(R.id.txt_lieu_farms);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });

        }


    }
}
