import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {JwtRequest, JwtResponse} from "../dto/jwt/jwt";

@Injectable({providedIn: 'root'})
export class AuthorizationGateway {

    private readonly httpClient = inject(HttpClient);

    generateJwtToken(jwtRequest: JwtRequest) {
       return this.httpClient.post<JwtResponse>(`https://dev-u6p6egz1bn2h0sye.eu.auth0.com/oauth/token`, jwtRequest);
    }




}
