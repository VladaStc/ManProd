import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOprema } from 'app/shared/model/oprema.model';

@Component({
    selector: 'jhi-oprema-detail',
    templateUrl: './oprema-detail.component.html'
})
export class OpremaDetailComponent implements OnInit {
    oprema: IOprema;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ oprema }) => {
            this.oprema = oprema;
        });
    }

    previousState() {
        window.history.back();
    }
}
