package com.gini.controllers;

import com.gini.dto.request.CarRequest;
import com.gini.dto.response.CarResponse;

import com.gini.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping("/car")
    @ResponseStatus(HttpStatus.CREATED)
    public CarResponse createCar(@RequestBody CarRequest carRequest) {
       return carService.createCar(carRequest);

    }


    @GetMapping("/car/{id}")
    public CarResponse getCar(@PathVariable String id) {
        return carService.getCar(id);

    }


}
