package com.gini.services;

import com.gini.model.Person;
import com.gini.service.PersonServiceGrpc.PersonServiceImplBase;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class TestService extends PersonServiceImplBase {

    @Override
    public void getPerson(Empty request, StreamObserver<Person> responseObserver) {

        var person = Person.newBuilder()
                .setName("gigel")
                .setAge(32)
                .build();

        responseObserver.onNext(person);
        responseObserver.onCompleted();


    }
}
