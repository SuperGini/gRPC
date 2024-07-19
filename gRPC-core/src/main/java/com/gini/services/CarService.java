package com.gini.services;

import com.gini.mapper.CarMapper;
import com.gini.repository.CarRepository;
import com.gini.request.CarRequest;
import com.gini.request.CarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public CarResponse saveCar(CarRequest carRequest) {

        var car  = CarMapper.mapFrom(carRequest);
        var carDb = carRepository.save(car);

        return CarMapper.mapTo(carDb, carRequest);

    }


//    public CarResponse getCar(CarRequest carRequest) {
//
//
//
//    }




}
