package com.example.projetointegracao.models.enums;

import com.example.projetointegracao.models.Line;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ProductEnum {
    CRONOS_6001_A(new Line("Cronos", "CronosOld", "Cronos 6001-A")),
    CRONOS_6003(new Line("Cronos", "CronosOld", "Cronos 6003")),
    CRONOS_7023(new Line("Cronos", "CronosOld", "Cronos 7023")),

    CRONOS_6021L(new Line("Cronos", "Cronos L", "Cronos 6021L")),
    CRONOS_7023L(new Line("Cronos", "Cronos L", "Cronos 7023L")),

    CRONOS_6001_NG(new Line("Cronos", "Cronos-NG", "Cronos 6001-NG")),
    CRONOS_6003_NG(new Line("Cronos", "Cronos-NG", "Cronos 6003-NG")),
    CRONOS_6021_NG(new Line("Cronos", "Cronos-NG", "Cronos 6021-NG")),
    CRONOS_6031_NG(new Line("Cronos", "Cronos-NG", "Cronos 6031-NG")),
    CRONOS_7021_NG(new Line("Cronos", "Cronos-NG", "Cronos 7021-NG")),
    CRONOS_7023_NG(new Line("Cronos", "Cronos-NG", "Cronos 7023-NG")),

    ARES_7021(new Line("Ares", "Ares TB", "ARES 7021")),
    ARES_7031(new Line("Ares", "Ares TB", "ARES 7031")),
    ARES_7023(new Line("Ares", "Ares TB", "ARES 7023")),

    ARES_8023_15(new Line("Ares", "Ares THS", "ARES 8023 15")),
    ARES_8023_200(new Line("Ares", "Ares THS", "ARES 8023 200")),
    ARES_8023_2_5(new Line("Ares", "Ares THS", "ARES 8023 2,5"));

    private Line line;

    ProductEnum(Line line) {
        this.line = line;
    }

    public Line getProduct() {
        return line;
    }

    public static List<Line> getAllProducts() {
        return Arrays.stream(ProductEnum.values())
                .map(ProductEnum::getProduct)
                .collect(Collectors.toList());
    }
}
