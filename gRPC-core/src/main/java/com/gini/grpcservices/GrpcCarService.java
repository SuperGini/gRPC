package com.gini.grpcservices;


import com.gini.exceptions.CarNotFoundException;
import com.gini.request.CarRequest;
import com.gini.request.CarResponse;
import com.gini.request.get.CarGetResponse;
import com.gini.request.get.CarId;
import com.gini.request.update.CarUpdateVersion;
import com.gini.services.CarService;
import com.gini.services.CarServiceGrpc;
import com.google.common.util.concurrent.Uninterruptibles;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import java.time.Duration;

@RequiredArgsConstructor
@GrpcService
public class GrpcCarService extends CarServiceGrpc.CarServiceImplBase {

    private final CarService carService;

    @Override
    @PreAuthorize("hasAuthority('SCOPE_write')")
    public void createCar(CarRequest request, StreamObserver<CarResponse> responseObserver) {
        var carResponse = carService.saveCar(request);

        responseObserver.onNext(carResponse);
        responseObserver.onCompleted();
    }


    @Override
    @PreAuthorize("hasAuthority('SCOPE_read')")
    public void getCar(CarId request, StreamObserver<CarGetResponse> responseObserver) {
        var carResponse = carService.getCar(request);

        responseObserver.onNext(carResponse);
        responseObserver.onCompleted();
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_write')")
    public void updateCarVersion(CarUpdateVersion request, StreamObserver<Empty> responseObserver) {

        try {
            carService.updateCarVersion(request);
            responseObserver.onNext(Empty.getDefaultInstance()); // we don't return anything this is why we send an empty instance
            responseObserver.onCompleted();
        } catch (DataIntegrityViolationException e) {
            throw new CarNotFoundException("car id not found", e);
        }
    }


    @Override
    @PreAuthorize("hasAuthority('SCOPE_read')")
    public void getAllCarsAsStream(Empty request, StreamObserver<CarGetResponse> responseObserver) {

        var carList = carService.getAllCars();

        carList.forEach(car -> { //to simulate streaming
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(2L));
            responseObserver.onNext(car);
        });
        responseObserver.onCompleted();
    }
}
