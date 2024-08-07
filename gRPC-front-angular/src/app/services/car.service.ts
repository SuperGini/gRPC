import {inject, Injectable} from "@angular/core";
import {GrpcApiGateway} from "../gateway/grpcapi.gateway";

@Injectable({providedIn: "root"})
export class CarService {

    private readonly grpcApiGateway = inject(GrpcApiGateway);


    getCar(id: string) {
        return this.grpcApiGateway.getCar(id)
    }


}
