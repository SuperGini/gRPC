import {Component, inject} from "@angular/core";
import {CarService} from "../services/car.service";

@Component({
    selector: "test-component",
    templateUrl: "test.html",
    standalone: true
})
export class Test {

    private readonly carService = inject(CarService);


    getCar() {

        this.carService.getCar("83e4b0fd-15c7-45f3-b064-ad107c9b75bd")
            .subscribe(car => {
                console.log(car.manufacturerName + "+++++++");
            });

    }

}
