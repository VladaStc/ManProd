import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRadionica } from 'app/shared/model/radionica.model';

@Component({
    selector: 'jhi-radionica-detail',
    templateUrl: './radionica-detail.component.html'
})
export class RadionicaDetailComponent implements OnInit {
    radionica: IRadionica;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ radionica }) => {
            this.radionica = radionica;
        });
    }

    previousState() {
        window.history.back();
    }
}
