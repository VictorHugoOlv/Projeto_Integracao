package com.example.projetointegracao.models.enums;

public enum LineEnum {

    ARES("Ares"),
    CRONOS("Cronos");

    private final String lineName;

    LineEnum(String name) {
        this.lineName = name;
    }

    public String getLineName() {
        return lineName;
    }

}
