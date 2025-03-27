package com.example.projetointegracao.models;

public class Line {
    private String line;
    private String category;
    private String model;

    public Line() {
    }

    public Line(String line, String category, String model) {
        this.line = line;
        this.category = category;
        this.model = model;
    }

    public String getLine() {
        return line;
    }

    public String getCategory() {
        return category;
    }

    public String getModel() {
        return model;
    }

}
