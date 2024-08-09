import {Component} from "@angular/core";
import {RouterOutlet} from "@angular/router";
import {MatIcon} from "@angular/material/icon";

@Component({
    selector: "home-componenet",
    templateUrl: "./home.html",
    styleUrl: "./home.css",
    standalone: true,
    imports: [
        RouterOutlet,
        MatIcon
    ]
})
export class Home {

}
