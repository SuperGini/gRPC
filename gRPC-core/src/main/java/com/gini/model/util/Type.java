package com.gini.model.util;

import lombok.Getter;

@Getter
public enum Type {

    COUPE("coupe"),
    HATCHBACK("hatchback"),
    LIMOUSINE("limousine"),
    MINIVAN("minivan"),
    CABRIOLET("cabriolet"),
    SEDAN("sedan"),
    ROADSTER("roadster");

    private final String bodyType;

    Type(String bodyType) {
        this.bodyType = bodyType;
    }
}
