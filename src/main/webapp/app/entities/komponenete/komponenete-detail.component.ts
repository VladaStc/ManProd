import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKomponenete } from 'app/shared/model/komponenete.model';

@Component({
    selector: 'jhi-komponenete-detail',
    templateUrl: './komponenete-detail.component.html'
})
export class KomponeneteDetailComponent implements OnInit {
    komponenete: IKomponenete;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ komponenete }) => {
            this.komponenete = komponenete;
        });
    }

    previousState() {
        window.history.back();
    }
}
