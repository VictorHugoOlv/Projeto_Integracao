package com.example.projetointegracao.models.enums;


import java.util.Arrays;

public enum CategoryEnum {

    CRONOS_Old("CronosOld", LineEnum.CRONOS),
    CRONOS_L("Cronos L", LineEnum.CRONOS),
    CRONOS_NG("Cronos-NG", LineEnum.CRONOS),
    ARES_TB("Ares TB", LineEnum.ARES),
    ARES_TH("Ares THS", LineEnum.ARES);

    private final String categoryName;
    private final LineEnum lineName;

    CategoryEnum(String categoryName, LineEnum lineEnum) {
        this.categoryName = categoryName;
        this.lineName = lineEnum;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public LineEnum getLineName() {
        return lineName;
    }

    public static CategoryEnum[] getCategoryByLine(LineEnum lineName) {
        return Arrays.stream(values()).
                filter(category -> category.getLineName() == lineName).
                toArray(CategoryEnum[]::new);
    }

}
