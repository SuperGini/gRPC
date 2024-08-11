import {Component, inject, OnInit, signal} from "@angular/core";
import {MatIcon} from "@angular/material/icon";
import {CarResponse} from "../../../dto/response";
import {CarService} from "../../../services/car.service";

@Component({
    selector: "all-car-component",
    templateUrl: "allcarpage.html",
    styleUrl: "allcarpage.css",
    imports: [
        MatIcon
    ],
    standalone: true
})
export class AllCarPage implements OnInit{

    cars = signal<CarResponse[]>(null);
    private readonly carService = inject(CarService);

    ngOnInit(): void {
        this.getAllCars();
    }

    getAllCars() {
        this.carService.getAllCars().subscribe(cars => {
            this.cars.set(cars);
        })
    }


}
