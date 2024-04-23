package com.example.harjoitusty;

import java.util.ArrayList;

public class MunicipalityStorage {
    private static MunicipalityStorage storage = null;
    private static ArrayList<Municipality> municipalities;
    private MunicipalityStorage() {
        municipalities = new ArrayList<>();
    }

    public static MunicipalityStorage getInstance() {
        if (storage == null) {
            storage = new MunicipalityStorage();
        }
        return storage;
    }

    public static ArrayList<Municipality> getMunicipalities() {
        return municipalities;
    }

    public void addMunicipality(Municipality municipality) {
        for (Municipality m : municipalities) {
            if (m.getMunicipalityName().equalsIgnoreCase(municipality.getMunicipalityName())) {
                return;
            }
        }
        municipalities.add(municipality);
    }
}
