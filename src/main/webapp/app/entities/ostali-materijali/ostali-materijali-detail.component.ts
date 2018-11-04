import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOstaliMaterijali } from 'app/shared/model/ostali-materijali.model';

@Component({
    selector: 'jhi-ostali-materijali-detail',
    templateUrl: './ostali-materijali-detail.component.html'
})
export class OstaliMaterijaliDetailComponent implements OnInit {
    ostaliMaterijali: IOstaliMaterijali;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ostaliMaterijali }) => {
            this.ostaliMaterijali = ostaliMaterijali;
        });
    }

    previousState() {
        window.history.back();
    }
}
