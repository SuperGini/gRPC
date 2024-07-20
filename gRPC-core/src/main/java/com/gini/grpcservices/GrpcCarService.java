package com.gini.grpcservices;


import com.gini.request.CarRequest;
import com.gini.request.CarResponse;
import com.gini.request.get.CarGetResponse;
import com.gini.request.get.CarId;
import com.gini.request.update.CarUpdateVersion;
import com.gini.services.CarService;
import com.gini.services.CarServiceGrpc;
import com.google.protobuf.Empty;
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


    @Override
    public void getCar(CarId request, StreamObserver<CarGetResponse> responseObserver) {
       var carResponse =  carService.getCar(request);

       responseObserver.onNext(carResponse);
       responseObserver.onCompleted();
    }

    @Override
    public void updateCarVersion(CarUpdateVersion request, StreamObserver<Empty> responseObserver) {

        carService.updateCarVersion(request);
        responseObserver.onNext(Empty.getDefaultInstance()); // we don't return anything this is why we send an empty instance
        responseObserver.onCompleted();

    }
}
