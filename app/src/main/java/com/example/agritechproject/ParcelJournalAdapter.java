package com.example.agritechproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agritechproject.Models.ParcelJournal;

import java.util.List;

public class ParcelJournalAdapter extends RecyclerView.Adapter<ParcelJournalAdapter.ParcelJournalViewHolder> {

    public onItemClickListener mListener;
    interface  onItemClickListener{
        void onItemClick(Integer position);
    }
    public void setOnItemClickListener(onItemClickListener listener){
        mListener=listener;
    }
    List<ParcelJournal> listParcels;
    Context context;
    public ParcelJournalAdapter(Context context, List<ParcelJournal>listParcels){
        this.context=context;
        this.listParcels=listParcels;
    }
    @NonNull
    @Override
    public ParcelJournalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.parcel_item,parent,false);
        ParcelJournalViewHolder parcelJournalViewHolder= new ParcelJournalViewHolder(view,mListener);
        return parcelJournalViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParcelJournalViewHolder holder, int position) {
        final ParcelJournal parcelJournal = listParcels.get(position);

        holder.txtIdParcel.setText(String.valueOf(parcelJournal.getId()));
        holder.txtNameParcel.setText(parcelJournal.getNom());
        holder.txtTemperatureParcel.setText( String.valueOf(parcelJournal.getTemperatureIdeale()));
        holder.txtHumiditeParcel.setText( String.valueOf(parcelJournal.getHumiditeIdeale()));
        holder.txtHumiditeSolParcel.setText( String.valueOf(parcelJournal.getHumiditeSolIdeale()));
        holder.txtSurface.setText( String.valueOf(parcelJournal.getSurface()));
        holder.txtTotaleImplantation.setText( String.valueOf(parcelJournal.getTotalImplantation()));
        holder.txtLibelleCulture.setText(parcelJournal.getLibelleCulture());
        holder.txtIdCulture.setText(String.valueOf(parcelJournal.getIdCulture()));
        holder.txtTemperatureJournal.setText( String.valueOf(parcelJournal.getTemperature()));
        holder.txtHumiditeJournal.setText( String.valueOf(parcelJournal.getHumidite()));
        holder.txtHumiditeSolJournal.setText( String.valueOf(parcelJournal.getHumidite_sol()));
        holder.iconHistorique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    /*Intent intent = new Intent(context, HistoriqueActivity.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return listParcels.size();
    }

    public static class ParcelJournalViewHolder extends RecyclerView.ViewHolder{
        TextView  txtIdParcel ,txtNameParcel,txtTemperatureParcel,txtHumiditeParcel,txtHumiditeSolParcel,txtLibelleCulture,
                txtIdCulture,txtSurface,txtTotaleImplantation,txtTemperatureJournal,txtHumiditeJournal,txtHumiditeSolJournal;
        ImageView iconHistorique;


        public ParcelJournalViewHolder(@NonNull View itemView,onItemClickListener listener) {
            super(itemView);
            txtIdParcel = itemView.findViewById(R.id.txt_id_parcelle);
            txtNameParcel = itemView.findViewById(R.id.txt_name_parcel);
            txtTemperatureParcel = itemView.findViewById(R.id.txt_tmp_parcel);
            txtHumiditeParcel = itemView.findViewById(R.id.txt_hum_parcel);
            txtHumiditeSolParcel = itemView.findViewById(R.id.txt_hum_sol_parcel);
            txtLibelleCulture = itemView.findViewById(R.id.libelle_culture);
            txtIdCulture = itemView.findViewById(R.id.id_culture);
            txtSurface = itemView.findViewById(R.id.txt_surface);
            txtTotaleImplantation = itemView.findViewById(R.id.txt_total_implantation);


            txtTemperatureJournal = itemView.findViewById(R.id.txt_tmp_journal);
            txtHumiditeJournal = itemView.findViewById(R.id.txt_hum_journal);
            txtHumiditeSolJournal = itemView.findViewById(R.id.txt_hum_sol_journal);


            iconHistorique= itemView.findViewById(R.id.icon_historique);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });


        }


    }
}
