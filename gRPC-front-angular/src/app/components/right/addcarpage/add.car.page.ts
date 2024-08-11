import {Component, inject, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {CarService} from "../../../services/car.service";
import {AddCarRequest} from "../../../dto/add.car";

@Component({
    selector: "add-car-componenet",
    templateUrl: "./addcarpage.html",
    styleUrl: "./addcarpage.scss",
    imports: [
        ReactiveFormsModule
    ],
    standalone: true
})
/**
 * https://angular.dev/guide/forms/reactive-forms#using-the-formbuilder-service-to-generate-controls
 * */
export class AddCarPage implements OnInit{
    form: FormGroup;
    private readonly formBuilder = inject(FormBuilder);
    private readonly carService = inject(CarService);

    ngOnInit(): void {
        this.form = this.formBuilder.group(
            {
                manufacturer: ['', Validators.required],
                model: ['', Validators.required],
                type: ['', Validators.required],
                productionYear: ['', Validators.required]
            });
    }

    addCar() {
        const carRequest = this.createCarRequest();


        this.carService.addCar(carRequest).subscribe(car => {
            console.log(car.manufacturerName);
            console.log(car.model);
            console.log(car.versions);
        })
    }

    private createCarRequest () {
        const carRequest : AddCarRequest = {
            manufacturer: this.form.value.manufacturer,
            model: this.form.value.model,
            type: this.form.value.type,
            productionYear: this.form.value.productionYear
        }
        return carRequest;
    }
}
