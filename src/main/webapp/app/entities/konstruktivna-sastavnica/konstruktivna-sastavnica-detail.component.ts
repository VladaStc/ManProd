import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKonstruktivnaSastavnica } from 'app/shared/model/konstruktivna-sastavnica.model';

@Component({
    selector: 'jhi-konstruktivna-sastavnica-detail',
    templateUrl: './konstruktivna-sastavnica-detail.component.html'
})
export class KonstruktivnaSastavnicaDetailComponent implements OnInit {
    konstruktivnaSastavnica: IKonstruktivnaSastavnica;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ konstruktivnaSastavnica }) => {
            this.konstruktivnaSastavnica = konstruktivnaSastavnica;
        });
    }

    previousState() {
        window.history.back();
    }
}
