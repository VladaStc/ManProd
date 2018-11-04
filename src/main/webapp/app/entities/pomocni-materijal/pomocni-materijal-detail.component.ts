import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPomocniMaterijal } from 'app/shared/model/pomocni-materijal.model';

@Component({
    selector: 'jhi-pomocni-materijal-detail',
    templateUrl: './pomocni-materijal-detail.component.html'
})
export class PomocniMaterijalDetailComponent implements OnInit {
    pomocniMaterijal: IPomocniMaterijal;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pomocniMaterijal }) => {
            this.pomocniMaterijal = pomocniMaterijal;
        });
    }

    previousState() {
        window.history.back();
    }
}
