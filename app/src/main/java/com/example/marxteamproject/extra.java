package com.example.marxteamproject;

import java.util.HashMap;

public class extra {
    static String Type;
    static HashMap<String, String> tractorTypes = new HashMap<>();

    public static String getType(String ModelNum){

        tractorTypes.put("S650 (2014)", "Combines");
        tractorTypes.put("S690 (2021)", "Combines");
        tractorTypes.put("S760 (2023)", "Combines");
        tractorTypes.put("T670 (2021)", "Combines");
        tractorTypes.put("X9 1100 (2023)", "Combines");

// Drapers
        tractorTypes.put("HDF45 (2022)", "Drapers");
        tractorTypes.put("HDF50 (2023)", "Drapers");
        tractorTypes.put("HDR45 (2023)", "Drapers");
        tractorTypes.put("HDR50 (2023)", "Drapers");
        tractorTypes.put("RDF45 (2021)", "Drapers");

// Hay and Forage
        tractorTypes.put("450E (2023)", "Hay and Forage");

// Planters
        tractorTypes.put("1725C (2023)", "Planters");
        tractorTypes.put("1775NT (2023)", "Planters");
        tractorTypes.put("BD60 (2022)", "Planters");
        tractorTypes.put("DB44 (2021)", "Planters");
        tractorTypes.put("DR12 (2022)", "Planters");

// Row Crop Tractors
        tractorTypes.put("8R 230 (2022)", "Row Crop Tractors");
        tractorTypes.put("8R 250 (2021)", "Row Crop Tractors");
        tractorTypes.put("8R 310 (2022)", "Row Crop Tractors");
        tractorTypes.put("8RT 370 (2023)", "Row Crop Tractors");
        tractorTypes.put("8RX 410 (2021)", "Row Crop Tractors");

// Specialty Equipment
        tractorTypes.put("5075EN (Built)", "Specialty Equipment");
        tractorTypes.put("5075GV (Built)", "Specialty Equipment");
        tractorTypes.put("5105MH (Built)", "Specialty Equipment");
        tractorTypes.put("5130ML (Built)", "Specialty Equipment");
        tractorTypes.put("6120EH (Built)", "Specialty Equipment");

// Tilling Disks
        tractorTypes.put("2633VT (2022)", "Tilling Disks");
        tractorTypes.put("2660VT (2020)", "Tilling Disks");
        tractorTypes.put("2680HP (2021)", "Tilling Disks");

// Utility Tractors
        tractorTypes.put("5067E (2024)", "Utility Tractors");
        tractorTypes.put("5105M (2011)", "Utility Tractors");
        tractorTypes.put("6130M (2023)", "Utility Tractors");
        tractorTypes.put("6R 130 (2023)", "Utility Tractors");
        tractorTypes.put("6R 250 (2022)", "Utility Tractors");

Type = tractorTypes.get(ModelNum);
        return Type;

    }
}
