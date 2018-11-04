import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFinalniProizvod } from 'app/shared/model/finalni-proizvod.model';

@Component({
    selector: 'jhi-finalni-proizvod-detail',
    templateUrl: './finalni-proizvod-detail.component.html'
})
export class FinalniProizvodDetailComponent implements OnInit {
    finalniProizvod: IFinalniProizvod;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ finalniProizvod }) => {
            this.finalniProizvod = finalniProizvod;
        });
    }

    previousState() {
        window.history.back();
    }
}
