import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOdelenje } from 'app/shared/model/odelenje.model';

@Component({
    selector: 'jhi-odelenje-detail',
    templateUrl: './odelenje-detail.component.html'
})
export class OdelenjeDetailComponent implements OnInit {
    odelenje: IOdelenje;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ odelenje }) => {
            this.odelenje = odelenje;
        });
    }

    previousState() {
        window.history.back();
    }
}
