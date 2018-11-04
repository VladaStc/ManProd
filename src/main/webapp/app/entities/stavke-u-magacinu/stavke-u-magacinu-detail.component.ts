import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStavkeUMagacinu } from 'app/shared/model/stavke-u-magacinu.model';

@Component({
    selector: 'jhi-stavke-u-magacinu-detail',
    templateUrl: './stavke-u-magacinu-detail.component.html'
})
export class StavkeUMagacinuDetailComponent implements OnInit {
    stavkeUMagacinu: IStavkeUMagacinu;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ stavkeUMagacinu }) => {
            this.stavkeUMagacinu = stavkeUMagacinu;
        });
    }

    previousState() {
        window.history.back();
    }
}
