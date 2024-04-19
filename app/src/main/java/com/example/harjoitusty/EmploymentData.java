package com.example.harjoitusty;

import java.io.Serializable;

public class EmploymentData implements Serializable {
    private int year;
    private double employmentRate;


    public EmploymentData(int year, double employmentRate) {
        this.year = year;
        this.employmentRate = employmentRate;

    }

    public double getEmploymentRate() {
        return employmentRate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public void setEmploymentRate(double employmentRate) {
        this.employmentRate = employmentRate;
    }

}
