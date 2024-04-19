package com.example.harjoitusty;

import java.io.Serializable;

public class SufficiencyData implements Serializable {
    private int year;
    private float sufficiencyRate;

    public SufficiencyData(int year, float sufficiencyRate) {
        this.year = year;
        this.sufficiencyRate = sufficiencyRate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getSufficiencyRate() {
        return sufficiencyRate;
    }

    public void setSufficiencyRate(float sufficiencyRate) {
        this.sufficiencyRate = sufficiencyRate;
    }
}
