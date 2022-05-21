package com.example.agritechproject.Models;

import com.google.gson.annotations.SerializedName;

public class Parcel {
    @SerializedName("id")
    private Integer id ;
    @SerializedName("nom")
    private String nom;
    @SerializedName("surface")
    private Float surface;
    @SerializedName("totaleImplantation")
    private Integer totalImplantation;

    @SerializedName("etatArrosage")
    private Boolean etatArrosage;
    @SerializedName("typeArrosage")
    private Boolean typeArrosage;
    @SerializedName("temperatureIdeale")
    private Float temperatureIdeale;
    @SerializedName("humiditeIdeale")
    private Float humiditeIdeale;
    @SerializedName("humidite_solIdeale")
    private Float humiditeSolIdeale;

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getSurface() {
        return surface;
    }

    public void setSurface(Float surface) {
        this.surface = surface;
    }

    public Integer getTotalImplantation() {
        return totalImplantation;
    }

    public void setTotalImplantation(Integer totalImplantation) {
        this.totalImplantation = totalImplantation;
    }

    public Boolean getEtatArrosage() {
        return etatArrosage;
    }

    public void setEtatArrosage(Boolean etatArrosage) {
        this.etatArrosage = etatArrosage;
    }

    public Boolean getTypeArrosage() {
        return typeArrosage;
    }

    public void setTypeArrosage(Boolean typeArrosage) {
        this.typeArrosage = typeArrosage;
    }

    public Float getTemperatureIdeale() {
        return temperatureIdeale;
    }

    public void setTemperatureIdeale(Float temperatureIdeale) {
        this.temperatureIdeale = temperatureIdeale;
    }

    public Float getHumiditeIdeale() {
        return humiditeIdeale;
    }

    public void setHumiditeIdeale(Float humiditeIdeale) {
        this.humiditeIdeale = humiditeIdeale;
    }

    public Float getHumiditeSolIdeale() {
        return humiditeSolIdeale;
    }

    public void setHumiditeSolIdeale(Float humiditeSolIdeale) {
        this.humiditeSolIdeale = humiditeSolIdeale;
    }
}
