import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPoluproizvod } from 'app/shared/model/poluproizvod.model';

@Component({
    selector: 'jhi-poluproizvod-detail',
    templateUrl: './poluproizvod-detail.component.html'
})
export class PoluproizvodDetailComponent implements OnInit {
    poluproizvod: IPoluproizvod;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ poluproizvod }) => {
            this.poluproizvod = poluproizvod;
        });
    }

    previousState() {
        window.history.back();
    }
}
