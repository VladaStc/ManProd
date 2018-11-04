import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IPribori } from 'app/shared/model/pribori.model';
import { PriboriService } from './pribori.service';
import { IZahvati } from 'app/shared/model/zahvati.model';
import { ZahvatiService } from 'app/entities/zahvati';

@Component({
    selector: 'jhi-pribori-update',
    templateUrl: './pribori-update.component.html'
})
export class PriboriUpdateComponent implements OnInit {
    pribori: IPribori;
    isSaving: boolean;

    zahvatis: IZahvati[];
    trajanje: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private priboriService: PriboriService,
        private zahvatiService: ZahvatiService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pribori }) => {
            this.pribori = pribori;
            this.trajanje = this.pribori.trajanje != null ? this.pribori.trajanje.format(DATE_TIME_FORMAT) : null;
        });
        this.zahvatiService.query({ filter: 'pribori-is-null' }).subscribe(
            (res: HttpResponse<IZahvati[]>) => {
                if (!this.pribori.zahvati || !this.pribori.zahvati.id) {
                    this.zahvatis = res.body;
                } else {
                    this.zahvatiService.find(this.pribori.zahvati.id).subscribe(
                        (subRes: HttpResponse<IZahvati>) => {
                            this.zahvatis = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.pribori.trajanje = this.trajanje != null ? moment(this.trajanje, DATE_TIME_FORMAT) : null;
        if (this.pribori.id !== undefined) {
            this.subscribeToSaveResponse(this.priboriService.update(this.pribori));
        } else {
            this.subscribeToSaveResponse(this.priboriService.create(this.pribori));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPribori>>) {
        result.subscribe((res: HttpResponse<IPribori>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackZahvatiById(index: number, item: IZahvati) {
        return item.id;
    }
}
