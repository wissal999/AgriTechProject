package com.example.agritechproject.Models;

import com.google.gson.annotations.SerializedName;

public class ParcelJournal {
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

    @SerializedName("idCulture")
    private Integer idCulture;
    @SerializedName("nomCulture")
    private String libelleCulture;

    @SerializedName("date")
    private String date;
    @SerializedName("temperature")
    private  Float temperature;
    @SerializedName("humidite")
    private Float humidite;
    @SerializedName("humidite_sol")
    private Float humidite_sol;

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

    public Integer getIdCulture() {
        return idCulture;
    }

    public void setIdCulture(Integer idCulture) {
        this.idCulture = idCulture;
    }

    public String getLibelleCulture() {
        return libelleCulture;
    }

    public void setLibelleCulture(String libelleCulture) {
        this.libelleCulture = libelleCulture;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getHumidite() {
        return humidite;
    }

    public void setHumidite(Float humidite) {
        this.humidite = humidite;
    }

    public Float getHumidite_sol() {
        return humidite_sol;
    }

    public void setHumidite_sol(Float humidite_sol) {
        this.humidite_sol = humidite_sol;
    }
}
