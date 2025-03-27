package com.example.projetointegracao.controllers;

import com.example.projetointegracao.models.Product;
import com.example.projetointegracao.models.ProductCategory;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.*;
import java.util.stream.Collectors;

public class ProductController {

    private List<Product> productList = new ArrayList<>();

    @FXML
    private TreeView<String> modelsTreeView;

    @FXML
    private TitledPane modelsTitledPane;

    @FXML
    private ComboBox<String> selectionComboBox;


    @FXML
    public void initialize(){

        loadProductList();
        selectionComboBox.getItems().addAll(getDistinctLinesSorted());

        selectionComboBox.setOnAction(event -> {
            modelsTitledPane.setDisable(false);
            String selectedLineProducts = selectionComboBox.getValue();
            Set<ProductCategory> productCategories = getCategoriesAndModelsForLine(selectedLineProducts);
            loadTreeViewData(modelsTreeView, productCategories);

            modelsTitledPane.setExpanded(true);

        });
    }


    public List<Product> loadProductList() {
        productList.add(new Product("Cronos", "CronosOld", "Cronos 6001‑A"));
        productList.add(new Product("Cronos", "CronosOld", "Cronos 6003"));
        productList.add(new Product("Cronos", "CronosOld", "Cronos 7023"));

        productList.add(new Product("Cronos", "Cronos L", "Cronos 6021L"));
        productList.add(new Product("Cronos", "Cronos L", "Cronos 7023L"));

        productList.add(new Product("Cronos", "Cronos‑NG", "Cronos 6001‑NG"));
        productList.add(new Product("Cronos", "Cronos‑NG", "Cronos 6003‑NG"));
        productList.add(new Product("Cronos", "Cronos‑NG", "Cronos 6021‑NG"));
        productList.add(new Product("Cronos", "Cronos‑NG", "Cronos 6031‑NG"));
        productList.add(new Product("Cronos", "Cronos‑NG", "Cronos 7021‑NG"));
        productList.add(new Product("Cronos", "Cronos‑NG", "Cronos 7023‑NG"));

        productList.add(new Product("Ares", "Ares TB", "ARES 7021"));
        productList.add(new Product("Ares", "Ares TB", "ARES 7031"));
        productList.add(new Product("Ares", "Ares TB", "ARES 7023"));

        productList.add(new Product("Ares", " Ares THS", "ARES 8023 15"));
        productList.add(new Product("Ares", " Ares THS", "ARES 8023 200"));
        productList.add(new Product("Ares", " Ares THS", "ARES 8023 2,5"));

        return productList;
    }

    public Set<String> getDistinctLinesSorted() {
        return productList.stream()
                .map(Product::getLine)
                .filter(line -> !line.isEmpty())
                .distinct()
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }


    // Método para filtrar categorias e modelos de uma linha selecionada e retornar como Set
    public Set<ProductCategory> getCategoriesAndModelsForLine(String selectedLine) {
        Set<ProductCategory> productCategories = new HashSet<>();

        // Filtra as categorias e modelos para a linha selecionada
        productList.stream()
                .filter(p -> p.getLine().equals(selectedLine))  // Filtra pela linha selecionada
                .forEach(productLine -> {
                    if (!productLine.getCategory().isEmpty() && !productLine.getModel().isEmpty()) {
                        productCategories.add(new ProductCategory(productLine.getCategory(), productLine.getModel()));
                    }
                });

        return productCategories;
    }

    // Método para carregar os dados no TreeView
    public void loadTreeViewData(TreeView<String> treeView, Set<ProductCategory> productCategories) {

        // Cria um item raiz para a TreeView
        TreeItem<String> rootItem = new TreeItem<>("Categorias e Modelos");
        rootItem.setExpanded(true);

        // Agrupa as categorias
        Map<String, List<ProductCategory>> categoryMap = productCategories.stream()
                .collect(Collectors.groupingBy(ProductCategory::getCategory));

        // Preenche a TreeView com categorias e modelos
        categoryMap.forEach((category, models) -> {
            TreeItem<String> categoryItem = new TreeItem<>(category);
            models.forEach(model -> {
                TreeItem<String> modelItem = new TreeItem<>(model.getProduct());
                categoryItem.getChildren().add(modelItem);  // Adiciona o modelo à categoria
            });
            rootItem.getChildren().add(categoryItem);  // Adiciona a categoria à raiz
        });

        treeView.setRoot(rootItem);  // Configura a TreeView com a raiz
    }

}

