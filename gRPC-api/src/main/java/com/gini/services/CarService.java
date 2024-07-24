package com.gini.services;

import com.gini.dto.request.CarRequest;
import com.gini.dto.response.CarResponse;
import com.gini.maper.CarMapper;
import com.gini.request.Manufacturer;
import com.gini.request.VersionRequest;
import com.gini.request.get.CarId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarServiceGrpc.CarServiceBlockingStub carServiceStub;

    public CarResponse getCar(String id) {

        var carId = CarId.newBuilder()
                .setId(id)
                .build();

        var carResponse = carServiceStub
                .withDeadlineAfter(5, TimeUnit.SECONDS)
                .getCar(carId);

        return CarMapper.mapFrom(carResponse);

    }

    public CarResponse createCar(CarRequest request) {

        var carRequest = com.gini.request.CarRequest.newBuilder()
                .setModel(request.model())
                .setManufacturer(Manufacturer.newBuilder().setName(request.manufacturerName()).build())
                .setVersion(VersionRequest.newBuilder()
                        .setType(com.gini.request.Type.valueOf(request.version().type().name()))
                        .setProductionYear(request.version().productionYear())
                        .build())
                .build();

        var carResponse = carServiceStub.withDeadlineAfter(5, TimeUnit.SECONDS).createCar(carRequest);

        return CarMapper.generateCarResponse(carResponse);

    }

}
