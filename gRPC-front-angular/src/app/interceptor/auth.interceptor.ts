/**
 * https://angular.dev/guide/http/interceptors
 * */
import {HttpEvent, HttpHandlerFn, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

export function authInterceptor(req: HttpRequest<any>, next: HttpHandlerFn): Observable<HttpEvent<any>> {

    const httpHeaders = req.headers
                           .append("Authorization", "Bearer " + sessionStorage.getItem("id_token"));

    const newModifiedRequest = req.clone({headers: httpHeaders});

    return next(newModifiedRequest);
}
