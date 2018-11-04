import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAlati } from 'app/shared/model/alati.model';

@Component({
    selector: 'jhi-alati-detail',
    templateUrl: './alati-detail.component.html'
})
export class AlatiDetailComponent implements OnInit {
    alati: IAlati;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ alati }) => {
            this.alati = alati;
        });
    }

    previousState() {
        window.history.back();
    }
}
