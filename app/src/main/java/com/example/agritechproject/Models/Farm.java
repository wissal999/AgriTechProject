package com.example.agritechproject.Models;

import com.google.gson.annotations.SerializedName;

public class Farm {
    @SerializedName("id")
    public Integer idFerme;

    @SerializedName("nom")
    public String nomFerme;
    @SerializedName("lieu")
    public String lieuFerme;

    public Integer getIdFerme() {
        return idFerme;
    }

    public void setIdFerme(Integer idFerme) {
        this.idFerme = idFerme;
    }

    public String getNomFerme() {
        return nomFerme;
    }

    public void setNomFerme(String nomFerme) {
        this.nomFerme = nomFerme;
    }

    public String getLieuFerme() {
        return lieuFerme;
    }

    public void setLieuFerme(String lieuFerme) {
        this.lieuFerme = lieuFerme;
    }
}
