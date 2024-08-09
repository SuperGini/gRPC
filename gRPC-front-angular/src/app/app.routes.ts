import {Routes} from '@angular/router';
import {Login} from "./components/login/login";
import {Redirect} from "./components/redirect/redirect";
import {Home} from "./components/home/home";
import {GetCarPage} from "./components/right/getcarpage/get.car.page";
import {AddCarPage} from "./components/right/addcarpage/add.car.page";
import {AllCarPage} from "./components/right/allcarpage/all.car.page";

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
        // pathMatch: "full", -> do not add this when having children it will throw an error
        children: [
            {
                path: 'car',
                component: GetCarPage,
                pathMatch: "full",

            },
            {
                path: 'addcar',
                component: AddCarPage,
                pathMatch: "full"
            },
            {
                path: 'allcars',
                component: AllCarPage,
                pathMatch: "full"
            }
        ]

    }


];
