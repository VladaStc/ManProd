import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDobavljac } from 'app/shared/model/dobavljac.model';

@Component({
    selector: 'jhi-dobavljac-detail',
    templateUrl: './dobavljac-detail.component.html'
})
export class DobavljacDetailComponent implements OnInit {
    dobavljac: IDobavljac;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dobavljac }) => {
            this.dobavljac = dobavljac;
        });
    }

    previousState() {
        window.history.back();
    }
}
