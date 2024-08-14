import {Component, inject} from "@angular/core";
import {CarService} from "../../../services/car.service";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";

@Component({
    selector: "update-car-componenet",
    templateUrl: "./updatecarpage.html",
    styleUrl: "./updatecarpage.scss",
    imports: [
        ReactiveFormsModule
    ],
    standalone: true

})
export class UpdateCarPage {

    form: FormGroup;
    private readonly formBuilder = inject(FormBuilder);
    private readonly carService = inject(CarService);

    updateCar() {

    }
}
