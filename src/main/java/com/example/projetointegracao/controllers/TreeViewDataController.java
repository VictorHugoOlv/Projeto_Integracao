package com.example.projetointegracao.controllers;

public class TreeViewDataController {
        private String categoryName;
        private String productName;

        public TreeViewDataController(String categoryName, String productModel) {
            this.categoryName = categoryName;
            this.productName = productModel;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public String getProductName() {
            return productName;
        }

}
