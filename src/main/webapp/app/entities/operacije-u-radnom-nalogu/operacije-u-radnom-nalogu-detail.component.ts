import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOperacijeURadnomNalogu } from 'app/shared/model/operacije-u-radnom-nalogu.model';

@Component({
    selector: 'jhi-operacije-u-radnom-nalogu-detail',
    templateUrl: './operacije-u-radnom-nalogu-detail.component.html'
})
export class OperacijeURadnomNaloguDetailComponent implements OnInit {
    operacijeURadnomNalogu: IOperacijeURadnomNalogu;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ operacijeURadnomNalogu }) => {
            this.operacijeURadnomNalogu = operacijeURadnomNalogu;
        });
    }

    previousState() {
        window.history.back();
    }
}
