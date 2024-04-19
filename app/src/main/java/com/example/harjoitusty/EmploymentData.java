package com.example.harjoitusty;

import java.io.Serializable;

public class EmploymentData implements Serializable {
    private int year;
    private int employmentRate;


    public EmploymentData(int year, int employmentRate) {
        this.year = year;
        this.employmentRate = employmentRate;

    }

    public int getEmploymentRate() {
        return employmentRate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public void setEmploymentRate(int employmentRate) {
        this.employmentRate = employmentRate;
    }

}
