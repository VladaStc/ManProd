import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRadniNalog } from 'app/shared/model/radni-nalog.model';

@Component({
    selector: 'jhi-radni-nalog-detail',
    templateUrl: './radni-nalog-detail.component.html'
})
export class RadniNalogDetailComponent implements OnInit {
    radniNalog: IRadniNalog;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ radniNalog }) => {
            this.radniNalog = radniNalog;
        });
    }

    previousState() {
        window.history.back();
    }
}
