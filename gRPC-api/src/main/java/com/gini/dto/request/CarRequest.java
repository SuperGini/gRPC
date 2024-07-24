package com.gini.dto.request;

import com.gini.dto.Type;

public record CarRequest(
        String model,
        String manufacturerName,
        VersionRequest version
) {
    public record VersionRequest(Type type, Integer productionYear) {}


}
