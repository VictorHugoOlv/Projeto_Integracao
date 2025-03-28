package com.example.projetointegracao.models.enums;

import com.example.projetointegracao.models.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ProductEnum {
    CRONOS_6001_A("Cronos 6001-A", CategoryEnum.CRONOS_Old),
    CRONOS_6003("Cronos 6003", CategoryEnum.CRONOS_Old),
    CRONOS_7023("Cronos 7023", CategoryEnum.CRONOS_Old),

    CRONOS_6021L("Cronos 6021L", CategoryEnum.CRONOS_L),
    CRONOS_7023L("Cronos 7023", CategoryEnum.CRONOS_L),

    CRONOS_6001_NG("Cronos 6001-NG", CategoryEnum.CRONOS_NG),
    CRONOS_6003_NG("Cronos 6003-NG", CategoryEnum.CRONOS_NG),
    CRONOS_6021_NG("Cronos 6021-NG", CategoryEnum.CRONOS_NG),
    CRONOS_6031_NG("Cronos 6031-NG", CategoryEnum.CRONOS_NG),
    CRONOS_7021_NG("Cronos 7021-NG", CategoryEnum.CRONOS_NG),
    CRONOS_7023_NG("Cronos 7023-NG", CategoryEnum.CRONOS_NG),

    ARES_7021("ARES 7021", CategoryEnum.ARES_TB),
    ARES_7031("ARES 7031", CategoryEnum.ARES_TB),
    ARES_7023("ARES 7023", CategoryEnum.ARES_TB),

    ARES_8023_15("ARES 8023 15", CategoryEnum.ARES_TH),
    ARES_8023_200("ARES 8023 200", CategoryEnum.ARES_TH),
    ARES_8023_2_5("ARES 8023 2,5", CategoryEnum.ARES_TH);

    private String productName;
    private CategoryEnum category;

    ProductEnum(String productName, CategoryEnum category) {
        this.productName = productName;
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public static List<Product> getAllProducts() {
        return Arrays.stream(ProductEnum.values())
                .map(productEnum -> new Product(
                        productEnum.getCategory().getLineName().getLineName(),
                        productEnum.getCategory().getCategoryName(),
                        productEnum.getProductName()
                ))
                .collect(Collectors.toList());
    }

    public static ProductEnum[] getProductsByCategory(CategoryEnum category) {
        return Arrays.stream(values()).
                filter(product -> product.getCategory() == category).
                toArray(ProductEnum[]::new);
    }
}
