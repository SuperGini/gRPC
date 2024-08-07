import {Component} from "@angular/core";
import {NgOptimizedImage} from "@angular/common";

@Component({
    selector: "login-component",
    templateUrl: "./login.html",
    styleUrl: "./login.css",
    imports: [
        NgOptimizedImage
    ],
    standalone: true
})
export class Login {


    redirectOnLogin() {
        window.location.href = "https://dev-u6p6egz1bn2h0sye.eu.auth0.com/authorize?response_type=code&client_id=3S08T8G6kgWi1viOYtz36HcUNbSG9Hbp&scope=openid&redirect_uri=http://localhost:4200/autorized&code_challenge=QYPAZ5NU8yvtlQ9erXrUYR-T5AGCjCF47vN-KsaI2A8&code_challenge_method=S256";
    }


}
