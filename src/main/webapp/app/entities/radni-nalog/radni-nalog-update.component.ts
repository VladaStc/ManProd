import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRadniNalog } from 'app/shared/model/radni-nalog.model';
import { RadniNalogService } from './radni-nalog.service';

@Component({
    selector: 'jhi-radni-nalog-update',
    templateUrl: './radni-nalog-update.component.html'
})
export class RadniNalogUpdateComponent implements OnInit {
    radniNalog: IRadniNalog;
    isSaving: boolean;
    datumIVremeOtvaranja: string;
    datumIVremeZatvaranja: string;

    constructor(private radniNalogService: RadniNalogService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ radniNalog }) => {
            this.radniNalog = radniNalog;
            this.datumIVremeOtvaranja =
                this.radniNalog.datumIVremeOtvaranja != null ? this.radniNalog.datumIVremeOtvaranja.format(DATE_TIME_FORMAT) : null;
            this.datumIVremeZatvaranja =
                this.radniNalog.datumIVremeZatvaranja != null ? this.radniNalog.datumIVremeZatvaranja.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.radniNalog.datumIVremeOtvaranja =
            this.datumIVremeOtvaranja != null ? moment(this.datumIVremeOtvaranja, DATE_TIME_FORMAT) : null;
        this.radniNalog.datumIVremeZatvaranja =
            this.datumIVremeZatvaranja != null ? moment(this.datumIVremeZatvaranja, DATE_TIME_FORMAT) : null;
        if (this.radniNalog.id !== undefined) {
            this.subscribeToSaveResponse(this.radniNalogService.update(this.radniNalog));
        } else {
            this.subscribeToSaveResponse(this.radniNalogService.create(this.radniNalog));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRadniNalog>>) {
        result.subscribe((res: HttpResponse<IRadniNalog>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
