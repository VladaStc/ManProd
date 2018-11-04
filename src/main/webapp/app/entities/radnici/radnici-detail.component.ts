import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRadnici } from 'app/shared/model/radnici.model';

@Component({
    selector: 'jhi-radnici-detail',
    templateUrl: './radnici-detail.component.html'
})
export class RadniciDetailComponent implements OnInit {
    radnici: IRadnici;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ radnici }) => {
            this.radnici = radnici;
        });
    }

    previousState() {
        window.history.back();
    }
}
