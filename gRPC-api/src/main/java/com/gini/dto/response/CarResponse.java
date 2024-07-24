package com.gini.dto.response;

import com.gini.dto.Type;

import java.util.List;

public record CarResponse(
        String id,
        String model,
        String manufacturerName,
        List<VersionResponse> versions

) {

    public record VersionResponse(Type type, Integer productionYear) {
    }


}
