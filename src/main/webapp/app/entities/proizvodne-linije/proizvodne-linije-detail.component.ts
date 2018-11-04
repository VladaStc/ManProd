import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProizvodneLinije } from 'app/shared/model/proizvodne-linije.model';

@Component({
    selector: 'jhi-proizvodne-linije-detail',
    templateUrl: './proizvodne-linije-detail.component.html'
})
export class ProizvodneLinijeDetailComponent implements OnInit {
    proizvodneLinije: IProizvodneLinije;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ proizvodneLinije }) => {
            this.proizvodneLinije = proizvodneLinije;
        });
    }

    previousState() {
        window.history.back();
    }
}
