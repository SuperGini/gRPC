import {Component, inject, signal} from "@angular/core";
import {MatIcon} from "@angular/material/icon";
import {CarResponse} from "../../../dto/response";
import {FormsModule} from "@angular/forms";
import {CarService} from "../../../services/car.service";

@Component({
    selector: "get-car-component",
    templateUrl: "./getcarpage.html",
    styleUrl: "./getcarpage.css",
    imports: [
        MatIcon,
        FormsModule
    ],
    standalone: true
})
export class GetCarPage {

    carId: string;
    readonly car = signal<CarResponse>(null);
    private readonly carService = inject(CarService);

     getCar() {
        const carId = this.carId;
         console.log(carId);
         this.carService.getCar(carId).subscribe(car => {
             this.car.set(car);
         })

     }

}
