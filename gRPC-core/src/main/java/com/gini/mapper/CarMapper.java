package com.gini.mapper;

import com.gini.model.Car;
import com.gini.model.Manufacturer;
import com.gini.model.Version;
import com.gini.request.CarRequest;
import com.gini.request.CarResponse;
import com.gini.response.TypeResponse;
import com.gini.response.VersionResponse;

import java.util.List;

public class CarMapper {

    public static Car mapFrom(CarRequest carRequest) {

        var manufacturer = Manufacturer.builder()
                .manufacturerName(carRequest.getManufacturer().getName())
                .build();


        var version = Version.builder()
                .productionYear(carRequest.getVersion().getProductionYear())
                .type(com.gini.model.util.Type.valueOf(carRequest.getVersion().getType().name()))
                .build();

        return Car.builder()
                .manufacturer(manufacturer)
                .carVersions(List.of(version))
                .model(carRequest.getModel())
                .build();

    }

    public static CarResponse mapTo(Car car, CarRequest request) {

        var version = VersionResponse.newBuilder()
                .setType(TypeResponse.valueOf(request.getVersion().getType().name()))
                .setProductionYear(request.getVersion().getProductionYear())
                .build();

        return CarResponse.newBuilder()
                .setId(car.getId())
                .setModel(car.getModel())
                .setManufacturer(car.getManufacturer().getManufacturerName())
                .setVersion(version)
                .build();
    }

}
