import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMerniAlati } from 'app/shared/model/merni-alati.model';
import { MerniAlatiService } from './merni-alati.service';

@Component({
    selector: 'jhi-merni-alati-update',
    templateUrl: './merni-alati-update.component.html'
})
export class MerniAlatiUpdateComponent implements OnInit {
    merniAlati: IMerniAlati;
    isSaving: boolean;
    trajanje: string;
    bazdarenje: string;

    constructor(private merniAlatiService: MerniAlatiService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ merniAlati }) => {
            this.merniAlati = merniAlati;
            this.trajanje = this.merniAlati.trajanje != null ? this.merniAlati.trajanje.format(DATE_TIME_FORMAT) : null;
            this.bazdarenje = this.merniAlati.bazdarenje != null ? this.merniAlati.bazdarenje.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.merniAlati.trajanje = this.trajanje != null ? moment(this.trajanje, DATE_TIME_FORMAT) : null;
        this.merniAlati.bazdarenje = this.bazdarenje != null ? moment(this.bazdarenje, DATE_TIME_FORMAT) : null;
        if (this.merniAlati.id !== undefined) {
            this.subscribeToSaveResponse(this.merniAlatiService.update(this.merniAlati));
        } else {
            this.subscribeToSaveResponse(this.merniAlatiService.create(this.merniAlati));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMerniAlati>>) {
        result.subscribe((res: HttpResponse<IMerniAlati>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
