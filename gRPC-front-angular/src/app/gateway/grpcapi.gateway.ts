import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {CarResponse} from "../dto/response";
import {AddCarRequest} from "../dto/add.car";


@Injectable({providedIn: "root"})
export class GrpcApiGateway {

    private readonly grpcApiClient = inject(HttpClient);

    getCar(id: string){
        return this.grpcApiClient.get<CarResponse>(`http://localhost:8080/car/${id}`);
    }

    addCar(carRequest: AddCarRequest) {
        return this.grpcApiClient.post<CarResponse>(`http://localhost:8080/car`, carRequest)
    }

    getAllCars(){
        return this.grpcApiClient.get<CarResponse[]>(`http://localhost:8080/cars`);
    }




}
