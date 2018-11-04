import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMerniAlati } from 'app/shared/model/merni-alati.model';

@Component({
    selector: 'jhi-merni-alati-detail',
    templateUrl: './merni-alati-detail.component.html'
})
export class MerniAlatiDetailComponent implements OnInit {
    merniAlati: IMerniAlati;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ merniAlati }) => {
            this.merniAlati = merniAlati;
        });
    }

    previousState() {
        window.history.back();
    }
}
