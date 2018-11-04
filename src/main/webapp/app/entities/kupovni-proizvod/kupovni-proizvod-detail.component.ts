import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKupovniProizvod } from 'app/shared/model/kupovni-proizvod.model';

@Component({
    selector: 'jhi-kupovni-proizvod-detail',
    templateUrl: './kupovni-proizvod-detail.component.html'
})
export class KupovniProizvodDetailComponent implements OnInit {
    kupovniProizvod: IKupovniProizvod;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ kupovniProizvod }) => {
            this.kupovniProizvod = kupovniProizvod;
        });
    }

    previousState() {
        window.history.back();
    }
}
