package com.example.projetointegracao.models;

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