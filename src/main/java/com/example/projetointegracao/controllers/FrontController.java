package com.example.projetointegracao.controllers;

import javafx.util.StringConverter;
import org.example.controllers.LineController;
import org.example.controllers.CategoryController;
import org.example.controllers.ProductController;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.example.models.Product;
import org.example.models.Category;
import org.example.models.Line;


import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.*;
import java.util.stream.Collectors;

public class FrontController {
    EntityManager em = Persistence.createEntityManagerFactory("example").createEntityManager();
    private LineController lineController;
    private CategoryController categoryController;
    private ProductController productController;

    private  List<Line> lineList;

    @FXML
    private TreeView<String> modelsTreeView;

    @FXML
    private TitledPane modelsTitledPane;

    @FXML
    private ComboBox<String> selectionComboBox;

    public FrontController() {
        this.lineController = new LineController(em);
        this.categoryController = new CategoryController(em);
        this.productController = new ProductController(em);
    }

    @FXML
    public void initialize() {
        lineList = lineController.getAllLines();

        selectionComboBox.getItems().addAll(getDistinctLinesSorted());

        selectionComboBox.setOnAction(event -> {
            modelsTitledPane.setDisable(false);
            String selectedLine = selectionComboBox.getValue();
            Set<TreeViewDataController> productCategories = getCategoriesAndProductsForLine(selectedLine);
            loadTreeViewData(modelsTreeView, productCategories);

            modelsTitledPane.setExpanded(true);

        });
    }


    public Set<String> getDistinctLinesSorted() {
        return lineList.stream()
                .map(Line::getLineName)
                .filter(line -> !line.isEmpty())
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }


    public Set<TreeViewDataController> getCategoriesAndProductsForLine(String selectedLine) {
        Set<TreeViewDataController> productCategories = new HashSet<>();
        Optional<Line> selectedLineObj = lineList.stream()
                .filter(line -> line.getLineName().equals(selectedLine))
                .findFirst();

        if (selectedLineObj.isPresent()) {
            List<Category> categories = categoryController.getCategoryBySelectedLine(selectedLineObj.get());

            for (Category category : categories) {
                List<Product> products = productController.getProductBySelectedCategory(category);
                for (Product product : products) {
                    productCategories.add(new TreeViewDataController(category.getCategoryName(), product.getProductName()));
                }
            }
        }
        return productCategories;
    }

    public void loadTreeViewData(TreeView<String> treeView, Set<TreeViewDataController> productCategories) {

        TreeItem<String> rootItem = new TreeItem<>("Categorias e Modelos");
        rootItem.setExpanded(true);

        Map<String, List<TreeViewDataController>> categoryMap = productCategories.stream()
                .collect(Collectors.groupingBy(TreeViewDataController::getCategoryName));

        categoryMap.forEach((category, models) -> {
            TreeItem<String> categoryItem = new TreeItem<>(category);
            models.forEach(model -> {
                TreeItem<String> modelItem = new TreeItem<>(model.getProductName());
                categoryItem.getChildren().add(modelItem);
            });
            rootItem.getChildren().add(categoryItem);
        });

        treeView.setRoot(rootItem);
    }

}

