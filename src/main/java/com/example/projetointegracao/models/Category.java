package com.example.projetointegracao.models;

public class Category {
    private final String category;
    private final String product;

    public Category(String category, String model) {
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