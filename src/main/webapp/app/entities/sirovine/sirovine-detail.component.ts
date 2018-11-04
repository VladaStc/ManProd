import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISirovine } from 'app/shared/model/sirovine.model';

@Component({
    selector: 'jhi-sirovine-detail',
    templateUrl: './sirovine-detail.component.html'
})
export class SirovineDetailComponent implements OnInit {
    sirovine: ISirovine;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sirovine }) => {
            this.sirovine = sirovine;
        });
    }

    previousState() {
        window.history.back();
    }
}
