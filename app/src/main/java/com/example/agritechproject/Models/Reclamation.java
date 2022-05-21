package com.example.agritechproject.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Reclamation {
    @SerializedName("id")
    private Integer id;

    @SerializedName("nomFermier")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("description")
    private String description;
    @SerializedName("resolution")
    private String resolution;
    @SerializedName("etatReclamation")
    private String etatReclamtion;
    @SerializedName("typeProbleme")
    private ArrayList<TypeProbleme> typeProbleme;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getEtatReclamtion() {
        return etatReclamtion;
    }

    public void setEtatReclamtion(String etatReclamtion) {
        this.etatReclamtion = etatReclamtion;
    }

    public ArrayList<TypeProbleme> getTypeProbleme() {
        return typeProbleme;
    }

    public void setTypeProbleme(ArrayList<TypeProbleme> typeProbleme) {
        this.typeProbleme = typeProbleme;
    }
}
