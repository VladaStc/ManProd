import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITransakcijeUMagacinu } from 'app/shared/model/transakcije-u-magacinu.model';

@Component({
    selector: 'jhi-transakcije-u-magacinu-detail',
    templateUrl: './transakcije-u-magacinu-detail.component.html'
})
export class TransakcijeUMagacinuDetailComponent implements OnInit {
    transakcijeUMagacinu: ITransakcijeUMagacinu;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ transakcijeUMagacinu }) => {
            this.transakcijeUMagacinu = transakcijeUMagacinu;
        });
    }

    previousState() {
        window.history.back();
    }
}
