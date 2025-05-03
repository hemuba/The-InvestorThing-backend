package com.stockmanager.backend.model;

public enum Sectors {
    TECH("Technology"),
    ENERGY("Energy"),
    HEALTH("Healthcare"),
    CONSUMER("Consumer");

    String description;

    Sectors(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public static Sectors fromDescription(String description){
        for (Sectors sec : Sectors.values()){
            if (sec.getDescription().equalsIgnoreCase(description)){
                return sec;
            }
        }
        return null; // to adjust
    }
}
