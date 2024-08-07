import { Routes } from '@angular/router';
import {Login} from "./components/login/login";
import {Redirect} from "./components/redirect/redirect";
import {Home} from "./components/home/home";

export const routes: Routes = [

    {
        path: "",
        component: Login,
        pathMatch: "full",
    },

    {
        path: "autorized",
        component: Redirect,
        pathMatch: "full",
    },

    {
        path: "home",
        component: Home,
        pathMatch: "full"
    }


];
