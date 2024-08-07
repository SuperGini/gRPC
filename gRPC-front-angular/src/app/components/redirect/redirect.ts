import {AfterContentInit, Component, inject, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {AuthorizationService} from "../../services/authorization.service";

@Component({
    selector: "redirect-component",
    template: `
    <div>REDIRECT...............</div>
    `,
    standalone: true
})
export class Redirect implements OnInit, AfterContentInit{

    private readonly activatedRoute= inject(ActivatedRoute);
    private readonly authorizationService = inject(AuthorizationService);
    private code: string;

    ngOnInit(): void {
        this.activatedRoute.queryParams.subscribe(params => {
            if (params?.['code']) {
                this.code = params['code'];
                console.log(`This is the auth code: ${this.code}`)
            }
        })
    }

    ngAfterContentInit(): void {
        this.authorizationService.generateJwtToken(this.code);
    }



}
