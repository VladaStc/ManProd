import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProizvodniPogoni } from 'app/shared/model/proizvodni-pogoni.model';

@Component({
    selector: 'jhi-proizvodni-pogoni-detail',
    templateUrl: './proizvodni-pogoni-detail.component.html'
})
export class ProizvodniPogoniDetailComponent implements OnInit {
    proizvodniPogoni: IProizvodniPogoni;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ proizvodniPogoni }) => {
            this.proizvodniPogoni = proizvodniPogoni;
        });
    }

    previousState() {
        window.history.back();
    }
}
