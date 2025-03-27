package com.example.projetointegracao.models;

// Classe auxiliar para armazenar Categoria e Modelo
public class ProductCategory {
    private final String category;
    private final String product;

    public ProductCategory(String category, String model) {
        this.category = category;
        this.product = model;
    }

    public String getCategory() {
        return category;
    }

    public String getProduct() {
        return product;
    }
}