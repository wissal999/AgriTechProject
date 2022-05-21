package com.example.agritechproject.Models;

import com.google.gson.annotations.SerializedName;

public class Culture {
    @SerializedName("idCulture")
    private Integer id;
    @SerializedName("nomCulture")
    private Integer libelleCulture;
    @SerializedName("temperatureIdeale")
    private Float temperatureIdeale;
    @SerializedName("humiditeIdeale")
    private Float humiditeIdeale;
    @SerializedName("humidite_solIdeale")
    private Float humidite_solIdeale;
}
