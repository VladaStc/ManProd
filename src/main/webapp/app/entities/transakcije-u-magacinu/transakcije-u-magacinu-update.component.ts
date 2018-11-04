import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITransakcijeUMagacinu } from 'app/shared/model/transakcije-u-magacinu.model';
import { TransakcijeUMagacinuService } from './transakcije-u-magacinu.service';

@Component({
    selector: 'jhi-transakcije-u-magacinu-update',
    templateUrl: './transakcije-u-magacinu-update.component.html'
})
export class TransakcijeUMagacinuUpdateComponent implements OnInit {
    transakcijeUMagacinu: ITransakcijeUMagacinu;
    isSaving: boolean;
    datum: string;

    constructor(private transakcijeUMagacinuService: TransakcijeUMagacinuService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ transakcijeUMagacinu }) => {
            this.transakcijeUMagacinu = transakcijeUMagacinu;
            this.datum = this.transakcijeUMagacinu.datum != null ? this.transakcijeUMagacinu.datum.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.transakcijeUMagacinu.datum = this.datum != null ? moment(this.datum, DATE_TIME_FORMAT) : null;
        if (this.transakcijeUMagacinu.id !== undefined) {
            this.subscribeToSaveResponse(this.transakcijeUMagacinuService.update(this.transakcijeUMagacinu));
        } else {
            this.subscribeToSaveResponse(this.transakcijeUMagacinuService.create(this.transakcijeUMagacinu));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITransakcijeUMagacinu>>) {
        result.subscribe(
            (res: HttpResponse<ITransakcijeUMagacinu>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
