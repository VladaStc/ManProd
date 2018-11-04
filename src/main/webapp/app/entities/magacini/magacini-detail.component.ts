import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMagacini } from 'app/shared/model/magacini.model';

@Component({
    selector: 'jhi-magacini-detail',
    templateUrl: './magacini-detail.component.html'
})
export class MagaciniDetailComponent implements OnInit {
    magacini: IMagacini;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ magacini }) => {
            this.magacini = magacini;
        });
    }

    previousState() {
        window.history.back();
    }
}
