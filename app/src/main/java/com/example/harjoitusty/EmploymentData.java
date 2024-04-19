package com.example.harjoitusty;

import java.io.Serializable;

public class EmploymentData implements Serializable {
    private int year;
    private float employmentRate;


    public EmploymentData(int year, float employmentRate) {
        this.year = year;
        this.employmentRate = employmentRate;

    }

    public float getEmploymentRate() {
        return employmentRate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setEmploymentRate(float employmentRate) {
        this.employmentRate = employmentRate;
    }

}
