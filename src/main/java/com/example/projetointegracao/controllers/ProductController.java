package com.example.projetointegracao.controllers;

import com.example.projetointegracao.models.Product;
import com.example.projetointegracao.models.ProductCategory;
import com.example.projetointegracao.models.enums.ProductEnum;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.*;
import java.util.stream.Collectors;

public class ProductController {

    private List<Product> productList = ProductEnum.getAllProducts();

    @FXML
    private TreeView<String> modelsTreeView;

    @FXML
    private TitledPane modelsTitledPane;

    @FXML
    private ComboBox<String> selectionComboBox;


    @FXML
    public void initialize(){

        selectionComboBox.getItems().addAll(getDistinctLinesSorted());

        selectionComboBox.setOnAction(event -> {
            modelsTitledPane.setDisable(false);
            String selectedLineProducts = selectionComboBox.getValue();
            Set<ProductCategory> productCategories = getCategoriesAndModelsForLine(selectedLineProducts);
            loadTreeViewData(modelsTreeView, productCategories);

            modelsTitledPane.setExpanded(true);

        });
    }


    public Set<String> getDistinctLinesSorted() {
        return productList.stream()
                .map(Product::getLine)
                .filter(line -> !line.isEmpty())
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }


    public Set<ProductCategory> getCategoriesAndModelsForLine(String selectedLine) {
        Set<ProductCategory> productCategories = new HashSet<>();

        productList.stream()
                .filter(p -> p.getLine().equals(selectedLine))
                .forEach(productLine -> {
                    if (!productLine.getCategory().isEmpty() && !productLine.getModel().isEmpty()) {
                        productCategories.add(new ProductCategory(productLine.getCategory(), productLine.getModel()));
                    }
                });

        return productCategories;
    }

    public void loadTreeViewData(TreeView<String> treeView, Set<ProductCategory> productCategories) {

        TreeItem<String> rootItem = new TreeItem<>("Categorias e Modelos");
        rootItem.setExpanded(true);

        Map<String, List<ProductCategory>> categoryMap = productCategories.stream()
                .collect(Collectors.groupingBy(ProductCategory::getCategory));

        categoryMap.forEach((category, models) -> {
            TreeItem<String> categoryItem = new TreeItem<>(category);
            models.forEach(model -> {
                TreeItem<String> modelItem = new TreeItem<>(model.getProduct());
                categoryItem.getChildren().add(modelItem);
            });
            rootItem.getChildren().add(categoryItem);
        });

        treeView.setRoot(rootItem);
    }

}

