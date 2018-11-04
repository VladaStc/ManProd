import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IZahvati } from 'app/shared/model/zahvati.model';

@Component({
    selector: 'jhi-zahvati-detail',
    templateUrl: './zahvati-detail.component.html'
})
export class ZahvatiDetailComponent implements OnInit {
    zahvati: IZahvati;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ zahvati }) => {
            this.zahvati = zahvati;
        });
    }

    previousState() {
        window.history.back();
    }
}
