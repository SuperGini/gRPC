package com.gini.services;

import com.gini.exceptions.CarNotFoundException;
import com.gini.mapper.CarMapper;
import com.gini.repository.CarRepository;
import com.gini.repository.CustomCarRepository;
import com.gini.request.CarRequest;
import com.gini.request.CarResponse;
import com.gini.request.get.CarGetResponse;
import com.gini.request.get.CarId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CustomCarRepository customCarRepository;

    public CarResponse saveCar(CarRequest carRequest) {

        var car  = CarMapper.mapFrom(carRequest);
        var carDb = carRepository.save(car);

        return CarMapper.mapTo(carDb, carRequest);

    }


    public CarGetResponse getCar(CarId carId) {

       var car = customCarRepository.findById(carId.getId());

       return Optional.ofNullable(car)
                .map(CarMapper::mapTo)
                .orElseThrow(() -> new CarNotFoundException("car id not found"));

    }




}
