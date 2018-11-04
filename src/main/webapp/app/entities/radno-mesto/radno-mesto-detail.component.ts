import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRadnoMesto } from 'app/shared/model/radno-mesto.model';

@Component({
    selector: 'jhi-radno-mesto-detail',
    templateUrl: './radno-mesto-detail.component.html'
})
export class RadnoMestoDetailComponent implements OnInit {
    radnoMesto: IRadnoMesto;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ radnoMesto }) => {
            this.radnoMesto = radnoMesto;
        });
    }

    previousState() {
        window.history.back();
    }
}
