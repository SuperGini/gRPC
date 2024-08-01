package com.gini.services;

import com.gini.dto.Type;
import com.gini.dto.request.CarRequest;
import com.gini.dto.response.CarResponse;
import com.gini.maper.CarMapper;
import com.gini.request.Manufacturer;
import com.gini.request.VersionRequest;
import com.gini.request.get.CarId;
import com.google.protobuf.Empty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public void getAllCars() {

        var result = carServiceStub.getAllCarsAsStream(Empty.getDefaultInstance());

        result.forEachRemaining(x -> System.out.println(x.toString()));

        result.forEachRemaining(x -> {

            List<CarResponse.VersionResponse> t = new ArrayList<>();


            x.getVersionsList()
                    .forEach(v -> t.add(
                                    new CarResponse.VersionResponse(
                                            Type.valueOf(v.getType().name()),
                                            v.getProductionYear()
                                    )
                            )
                    );


            var c = new CarResponse(
                    x.getId(),
                    x.getModel(),
                    x.getManufacturerName(),
                    t
            );
            System.out.println(c);

        });


    }

}
