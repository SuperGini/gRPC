import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {CarResponse} from "../dto/request";

@Injectable({providedIn: "root"})
export class GrpcApiGateway {

    private readonly grpcApiClient = inject(HttpClient);

    getCar(id: string){
        return this.grpcApiClient.get<CarResponse>(`http://localhost:8080/car/${id}`);
    }




}
