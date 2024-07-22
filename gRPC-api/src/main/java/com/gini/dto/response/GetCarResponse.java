package com.gini.dto.response;

import com.gini.response.VersionResponse;

import java.util.List;

public record GetCarResponse(

        String id,
        String model,
        String manufacturerName
//        List<VersionResponse> versions

) {
}
