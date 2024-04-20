package com.example.harjoitusty;

import java.io.Serializable;

public class Municipality implements Serializable {
    private String municipalityName;


    public Municipality(String municipalityName) {
        this.municipalityName = municipalityName;

    }

    public String getMunicipalityName() {
        return municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

}
