package com.gini.services;

import com.gini.exceptions.CarNotFoundException;
import com.gini.mapper.CarMapper;
import com.gini.model.Version;
import com.gini.model.util.Type;
import com.gini.repository.CarRepository;
import com.gini.repository.CustomCarRepository;
import com.gini.repository.VersionRepository;
import com.gini.request.CarRequest;
import com.gini.request.CarResponse;
import com.gini.request.get.CarGetResponse;
import com.gini.request.get.CarId;
import com.gini.request.update.CarUpdateVersion;
import com.gini.response.TypeResponse;
import com.gini.response.VersionResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CustomCarRepository customCarRepository;
    private final VersionRepository versionRepository;

    @Transactional
    public CarResponse saveCar(CarRequest carRequest) {

        var car = CarMapper.mapFrom(carRequest);
        var carDb = carRepository.save(car);

        return CarMapper.mapTo(carDb, carRequest);

    }

    @Transactional
    public CarGetResponse getCar(CarId carId) {

        var car = customCarRepository.findById(carId.getId());

        return Optional.ofNullable(car)
                .map(CarMapper::mapTo)
                .orElseThrow(() -> new CarNotFoundException("car id not found"));

    }


    @Transactional
    public void updateCarVersion(CarUpdateVersion carUpdateVersion) {

        var car = carRepository.getReferenceById(carUpdateVersion.getCarId().getId());

        var version = Version.builder()
                .type(Type.valueOf(carUpdateVersion.getType().name()))
                .productionYear(carUpdateVersion.getProductionYear())
                .car(car)
                .build();

        versionRepository.save(version);
    }


    public List<CarGetResponse> getAllCars() {

        var carList = customCarRepository.findAllCars();

        List<CarGetResponse> cars = new ArrayList<>();


        carList.stream()
                .map(CarMapper::mapTo)
                .forEach(cars::add);

//        carList.stream()
//                .map(x -> {
//
//                    var versions = new ArrayList<VersionResponse>();
//
//                    x.getCarVersions().stream()
//                            .map(y ->
//                                    VersionResponse.newBuilder()
//                                            .setType(TypeResponse.valueOf(y.getType().name()))
//                                            .setProductionYear(y.getProductionYear())
//                                            .build()
//                            )
//                            .forEach(versions::add);
//
//
//                    return CarGetResponse.newBuilder()
//                            .setId(x.getId())
//                            .setManufacturerName(x.getManufacturer().getManufacturerName())
//                            .setModel(x.getModel())
//                            .addAllVersions(versions)
//                            .build();
//                })
//
//                .forEach(cars::add);

        return cars;
    }


}
