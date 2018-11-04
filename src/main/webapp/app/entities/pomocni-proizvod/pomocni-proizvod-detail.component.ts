import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPomocniProizvod } from 'app/shared/model/pomocni-proizvod.model';

@Component({
    selector: 'jhi-pomocni-proizvod-detail',
    templateUrl: './pomocni-proizvod-detail.component.html'
})
export class PomocniProizvodDetailComponent implements OnInit {
    pomocniProizvod: IPomocniProizvod;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pomocniProizvod }) => {
            this.pomocniProizvod = pomocniProizvod;
        });
    }

    previousState() {
        window.history.back();
    }
}
