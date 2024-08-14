import {Component, inject, OnInit, signal} from "@angular/core";
import {Router, RouterOutlet} from "@angular/router";
import {MatIcon} from "@angular/material/icon";
import {NgClass} from "@angular/common";

@Component({
    selector: "home-componenet",
    templateUrl: "./home.html",
    styleUrl: "./home.css",
    standalone: true,
    imports: [
        RouterOutlet,
        MatIcon,
        NgClass
    ]
})
export class Home implements OnInit{

    private readonly router = inject(Router);
    readonly activePage = signal<Pages>(null);

    ngOnInit(): void {
        this.activePage.set(Pages.HOME)
    }


    toAddCarPage() {
        this.router.navigate(["home/addcar"]);
        this.activePage.set(Pages.ADDCAR)

    }

    toGetCarPage(){
        this.router.navigate(["home/car"]);
        this.activePage.set(Pages.GETCAR)
    }

    toAllCarsPage() {
        this.router.navigate(["home/allcars"]);
        this.activePage.set(Pages.GETALLCARS);
    }

    toUpdateCarPage() {
        this.router.navigate(["home/updatecar"]);
        this.activePage.set(Pages.UPDATECAR);
    }



}

enum Pages {
    ADDCAR = 'addCar',
    GETCAR= 'getCar',
    GETALLCARS = 'getAllCars',
    UPDATECAR = 'updateCar',
    HOME = 'home'

}
