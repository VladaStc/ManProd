import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPribori } from 'app/shared/model/pribori.model';

@Component({
    selector: 'jhi-pribori-detail',
    templateUrl: './pribori-detail.component.html'
})
export class PriboriDetailComponent implements OnInit {
    pribori: IPribori;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pribori }) => {
            this.pribori = pribori;
        });
    }

    previousState() {
        window.history.back();
    }
}
