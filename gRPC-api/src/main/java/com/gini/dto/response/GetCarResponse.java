package com.gini.dto.response;

import java.util.List;

public record GetCarResponse(
        String id,
        String model,
        String manufacturerName,
        List<VersionResponse> versions

) {

    public record VersionResponse(TypeResponse type, Integer productionYear) {}

    public enum TypeResponse {
        COUPE,
        HATCHBACK,
        LIMOUSINE,
        MINIVAN,
        CABRIOLET,
        SEDAN,
        ROADSTER,
    }

}
