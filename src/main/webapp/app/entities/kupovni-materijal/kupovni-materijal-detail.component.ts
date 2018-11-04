import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKupovniMaterijal } from 'app/shared/model/kupovni-materijal.model';

@Component({
    selector: 'jhi-kupovni-materijal-detail',
    templateUrl: './kupovni-materijal-detail.component.html'
})
export class KupovniMaterijalDetailComponent implements OnInit {
    kupovniMaterijal: IKupovniMaterijal;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ kupovniMaterijal }) => {
            this.kupovniMaterijal = kupovniMaterijal;
        });
    }

    previousState() {
        window.history.back();
    }
}
