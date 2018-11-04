import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOperacije } from 'app/shared/model/operacije.model';

@Component({
    selector: 'jhi-operacije-detail',
    templateUrl: './operacije-detail.component.html'
})
export class OperacijeDetailComponent implements OnInit {
    operacije: IOperacije;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ operacije }) => {
            this.operacije = operacije;
        });
    }

    previousState() {
        window.history.back();
    }
}
