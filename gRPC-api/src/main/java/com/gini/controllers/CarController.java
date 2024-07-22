package com.gini.controllers;

import com.gini.dto.response.GetCarResponse;
import com.gini.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;


    @GetMapping("/car/{id}")
    public GetCarResponse getCar(@PathVariable String id) {
        return carService.getCar(id);

    }


}
