import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IAlati } from 'app/shared/model/alati.model';
import { AlatiService } from './alati.service';
import { IZahvati } from 'app/shared/model/zahvati.model';
import { ZahvatiService } from 'app/entities/zahvati';

@Component({
    selector: 'jhi-alati-update',
    templateUrl: './alati-update.component.html'
})
export class AlatiUpdateComponent implements OnInit {
    alati: IAlati;
    isSaving: boolean;

    zahvatis: IZahvati[];
    trajanje: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private alatiService: AlatiService,
        private zahvatiService: ZahvatiService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ alati }) => {
            this.alati = alati;
            this.trajanje = this.alati.trajanje != null ? this.alati.trajanje.format(DATE_TIME_FORMAT) : null;
        });
        this.zahvatiService.query({ filter: 'alati-is-null' }).subscribe(
            (res: HttpResponse<IZahvati[]>) => {
                if (!this.alati.zahvati || !this.alati.zahvati.id) {
                    this.zahvatis = res.body;
                } else {
                    this.zahvatiService.find(this.alati.zahvati.id).subscribe(
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
        this.alati.trajanje = this.trajanje != null ? moment(this.trajanje, DATE_TIME_FORMAT) : null;
        if (this.alati.id !== undefined) {
            this.subscribeToSaveResponse(this.alatiService.update(this.alati));
        } else {
            this.subscribeToSaveResponse(this.alatiService.create(this.alati));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAlati>>) {
        result.subscribe((res: HttpResponse<IAlati>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
