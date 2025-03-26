package com.example.projetointegracao;

// Classe auxiliar para armazenar Categoria e Modelo
public class ProductCategory {
    private final String category;
    private final String model;

    public ProductCategory(String category, String model) {
        this.category = category;
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public String getModel() {
        return model;
    }
}