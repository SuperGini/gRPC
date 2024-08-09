import {Component, inject, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {SvgIconsService} from "./services/svgIcons.service";

@Component({
    selector: 'app-root',
    standalone: true,
    imports: [RouterOutlet],
    templateUrl: './app.component.html',
    styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

    private readonly svgIconService = inject(SvgIconsService);

    ngOnInit(): void {
       this.svgIconService.addIconsToRegistry();
    }

}
