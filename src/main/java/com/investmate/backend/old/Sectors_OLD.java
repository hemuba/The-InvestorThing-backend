package com.investmate.backend.old;

import com.investmate.backend.common.exception.NotFoundException;

public enum Sectors_OLD {
    TECHNOLOGY("Technology"),
    ENERGY("Energy"),
    HEALTHCARE("Healthcare"),
    CONSUMER("Consumer");
    // more to be added //

    final String description;

    Sectors_OLD(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public static Sectors_OLD fromDescription(String description){
        for (Sectors_OLD sector: Sectors_OLD.values()){
            if (sector.getDescription().equalsIgnoreCase(description)){
                return sector;
            }
        }
        throw  new NotFoundException("Sector " + description + " not found.");
    }
}
