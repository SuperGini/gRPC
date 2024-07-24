package com.gini.maper;

import com.gini.dto.Type;
import com.gini.dto.response.CarResponse;
import com.gini.request.get.CarGetResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CarMapper {

    public static CarResponse generateCarResponse(com.gini.request.CarResponse carResponse) {
        var cars = new ArrayList<CarResponse.VersionResponse>();

        cars.add(
                new CarResponse.VersionResponse(
                        Type.valueOf(carResponse.getVersion().getType().name()),
                        carResponse.getVersion().getProductionYear())
        );


        return new CarResponse(
                carResponse.getId(),
                carResponse.getModel(),
                carResponse.getManufacturer(),
                cars
        );
    }


    public static CarResponse mapFrom(CarGetResponse carResponse) {
        var cars = new ArrayList<CarResponse.VersionResponse>();
        carResponse.getVersionsList().forEach(car -> addCarToCarsList(car, cars));

        return new CarResponse(
                carResponse.getId(),
                carResponse.getModel(),
                carResponse.getManufacturerName(),
                cars
        );
    }

    private static void addCarToCarsList(com.gini.response.VersionResponse car, ArrayList<CarResponse.VersionResponse> cars) {
        var versionResponse = new CarResponse.VersionResponse(
                Type.valueOf(car.getType().name()),
                car.getProductionYear()
        );
        cars.add(versionResponse);
    }





}
