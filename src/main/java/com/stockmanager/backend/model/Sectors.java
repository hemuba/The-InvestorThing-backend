package com.stockmanager.backend.model;

public enum Sectors {
    TECHNOLOGY("Technology"),
    ENERGY("Energy"),
    HEALTHCARE("Healthcare"),
    CONSUMER("Consumer");
    // more to be added //

    final String description;

    Sectors(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public static Sectors fromDescription(String description){
        for (Sectors sector: Sectors.values()){
            if (sector.getDescription().equalsIgnoreCase(description)){
                return sector;
            }
        }
        throw  new IllegalArgumentException("Sector " + description + " not found.");
    }
}
