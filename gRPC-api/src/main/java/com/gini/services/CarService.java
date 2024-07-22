package com.gini.services;

import com.gini.dto.response.GetCarResponse;
import com.gini.request.get.CarId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarServiceGrpc.CarServiceBlockingStub carServiceStub;

    public GetCarResponse getCar(String id) {


        var carId = CarId.newBuilder()
                .setId(id)
                .build();

        var carResponse = carServiceStub.getCar(carId);


        return new GetCarResponse(
                carResponse.getId(),
                carResponse.getModel(),
                carResponse.getManufacturerName()
//                carResponse.getVersionsList()

        );


    }


}
