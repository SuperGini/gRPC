package com.gini.services;

import com.gini.dto.response.GetCarResponse;
import com.gini.dto.response.GetCarResponse.TypeResponse;
import com.gini.dto.response.GetCarResponse.VersionResponse;
import com.gini.request.get.CarId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarServiceGrpc.CarServiceBlockingStub carServiceStub;

    public GetCarResponse getCar(String id) {
        var cars = new ArrayList<VersionResponse>();
        var carId = CarId.newBuilder()
                .setId(id)
                .build();

        var carResponse = carServiceStub
                .withDeadlineAfter(5, TimeUnit.SECONDS)
                .getCar(carId);

        carResponse.getVersionsList().forEach(car -> addCarToCarsList(car, cars));

        return new GetCarResponse(
                carResponse.getId(),
                carResponse.getModel(),
                carResponse.getManufacturerName(),
                cars
        );

    }

    private void addCarToCarsList(com.gini.response.VersionResponse car, ArrayList<VersionResponse> cars) {
        var versionResponse = new VersionResponse(
                TypeResponse.valueOf(car.getType().name()),
                car.getProductionYear()
        );
        cars.add(versionResponse);
    }


}
