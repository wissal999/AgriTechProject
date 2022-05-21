package com.example.agritechproject.Models;

import com.google.gson.annotations.SerializedName;

public class TypeProbleme {

        @SerializedName("id")
        private Integer id;
        @SerializedName("nom")
        private String nom;
        private Boolean checked=false;

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

        public Boolean getChecked() {
                return checked;
        }

        public void setChecked(Boolean checked) {
                this.checked = checked;
        }
}
