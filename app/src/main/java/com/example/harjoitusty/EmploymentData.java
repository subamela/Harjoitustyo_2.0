package com.example.harjoitusty;

import java.io.Serializable;

public class EmploymentData implements Serializable {
    String employmentRate;
    String workplaceSelfSufficiency;

    public EmploymentData(String employmentRate, String workplaceSelfSufficiency) {
        this.employmentRate = employmentRate;
        this.workplaceSelfSufficiency = workplaceSelfSufficiency;
    }

    public String getEmploymentRate() {
        return employmentRate;
    }

    public void setEmploymentRate(String employmentRate) {
        this.employmentRate = employmentRate;
    }

    public String getWorkplaceSelfSufficiency() {
        return workplaceSelfSufficiency;
    }

    public void setWorkplaceSelfSufficiency(String workplaceSelfSufficiency) {
        this.workplaceSelfSufficiency = workplaceSelfSufficiency;
    }
}
