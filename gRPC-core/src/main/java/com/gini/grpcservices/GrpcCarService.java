package com.gini.grpcservices;

import com.gini.mapper.CarMapper;
import com.gini.request.CarRequest;
import com.gini.request.CarResponse;
import com.gini.services.CarService;
import com.gini.services.CarServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@RequiredArgsConstructor
@GrpcService
public class GrpcCarService extends CarServiceGrpc.CarServiceImplBase {

    private final CarService carService;

    @Override
    public void createCar(CarRequest request, StreamObserver<CarResponse> responseObserver) {

       var carResponse = carService.saveCar(request);

       responseObserver.onNext(carResponse);
       responseObserver.onCompleted();

    }
}
