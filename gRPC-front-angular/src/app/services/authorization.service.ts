import {inject, Injectable} from "@angular/core";
import {JwtRequest} from "../dto/jwt/jwt";
import {AuthorizationGateway} from "../gateway/authorization.gateway";
import {Router} from "@angular/router";
import {clientId} from "../util/clientId";

@Injectable({providedIn: 'root'})
export class AuthorizationService {

    private readonly authorizationGateway = inject(AuthorizationGateway);
    private readonly router = inject(Router);

    generateJwtToken(code: string) {

        const jwtRequest = this.createJwtRequest(code);

        this.authorizationGateway.generateJwtToken(jwtRequest)
            .subscribe(jwt => {
                sessionStorage.setItem('id_token', jwt.id_token);
                this.router.navigate(['/home']);
            });
    }

    private createJwtRequest(code: string): JwtRequest {
        const jwtRequest: JwtRequest = {
            client_id: clientId(),
            redirect_uri: "http://localhost:4200/autorized",
            grant_type: "authorization_code",
            code: code,
            code_verifier: "qPsH306-ZDDaOE8DFzVn05TkN3ZZoVmI_6x4LsVglQI"
        };

        return jwtRequest;
    }


}
