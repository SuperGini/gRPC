import {inject, Injectable} from "@angular/core";
import {GrpcApiGateway} from "../gateway/grpcapi.gateway";
import {AddCarRequest} from "../dto/add.car";

@Injectable({providedIn: "root"})
export class CarService {

    private readonly grpcApiGateway = inject(GrpcApiGateway);


    getCar(id: string) {
        return this.grpcApiGateway.getCar(id);
    }

    addCar(carRequest: AddCarRequest) {
        return this.grpcApiGateway.addCar(carRequest);
    }

    getAllCars() {
        return this.grpcApiGateway.getAllCars();
    }

}
