import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPotrosniMaterijal } from 'app/shared/model/potrosni-materijal.model';

@Component({
    selector: 'jhi-potrosni-materijal-detail',
    templateUrl: './potrosni-materijal-detail.component.html'
})
export class PotrosniMaterijalDetailComponent implements OnInit {
    potrosniMaterijal: IPotrosniMaterijal;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ potrosniMaterijal }) => {
            this.potrosniMaterijal = potrosniMaterijal;
        });
    }

    previousState() {
        window.history.back();
    }
}
