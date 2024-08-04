import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {Test} from "./components/test";

@Component({
  selector: 'app-root',
  standalone: true,
    imports: [RouterOutlet, Test],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

}
