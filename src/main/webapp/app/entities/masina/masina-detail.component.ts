import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMasina } from 'app/shared/model/masina.model';

@Component({
    selector: 'jhi-masina-detail',
    templateUrl: './masina-detail.component.html'
})
export class MasinaDetailComponent implements OnInit {
    masina: IMasina;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ masina }) => {
            this.masina = masina;
        });
    }

    previousState() {
        window.history.back();
    }
}
