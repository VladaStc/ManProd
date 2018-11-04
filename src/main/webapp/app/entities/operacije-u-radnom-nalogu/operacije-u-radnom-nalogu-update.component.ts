import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IOperacijeURadnomNalogu } from 'app/shared/model/operacije-u-radnom-nalogu.model';
import { OperacijeURadnomNaloguService } from './operacije-u-radnom-nalogu.service';
import { IRadniNalog } from 'app/shared/model/radni-nalog.model';
import { RadniNalogService } from 'app/entities/radni-nalog';

@Component({
    selector: 'jhi-operacije-u-radnom-nalogu-update',
    templateUrl: './operacije-u-radnom-nalogu-update.component.html'
})
export class OperacijeURadnomNaloguUpdateComponent implements OnInit {
    operacijeURadnomNalogu: IOperacijeURadnomNalogu;
    isSaving: boolean;

    radninalogs: IRadniNalog[];
    datumIVremePocetka: string;
    datumIVremeZavrsetka: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private operacijeURadnomNaloguService: OperacijeURadnomNaloguService,
        private radniNalogService: RadniNalogService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ operacijeURadnomNalogu }) => {
            this.operacijeURadnomNalogu = operacijeURadnomNalogu;
            this.datumIVremePocetka =
                this.operacijeURadnomNalogu.datumIVremePocetka != null
                    ? this.operacijeURadnomNalogu.datumIVremePocetka.format(DATE_TIME_FORMAT)
                    : null;
            this.datumIVremeZavrsetka =
                this.operacijeURadnomNalogu.datumIVremeZavrsetka != null
                    ? this.operacijeURadnomNalogu.datumIVremeZavrsetka.format(DATE_TIME_FORMAT)
                    : null;
        });
        this.radniNalogService.query({ filter: 'operacijeuradnomnalogu-is-null' }).subscribe(
            (res: HttpResponse<IRadniNalog[]>) => {
                if (!this.operacijeURadnomNalogu.radniNalog || !this.operacijeURadnomNalogu.radniNalog.id) {
                    this.radninalogs = res.body;
                } else {
                    this.radniNalogService.find(this.operacijeURadnomNalogu.radniNalog.id).subscribe(
                        (subRes: HttpResponse<IRadniNalog>) => {
                            this.radninalogs = [subRes.body].concat(res.body);
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
        this.operacijeURadnomNalogu.datumIVremePocetka =
            this.datumIVremePocetka != null ? moment(this.datumIVremePocetka, DATE_TIME_FORMAT) : null;
        this.operacijeURadnomNalogu.datumIVremeZavrsetka =
            this.datumIVremeZavrsetka != null ? moment(this.datumIVremeZavrsetka, DATE_TIME_FORMAT) : null;
        if (this.operacijeURadnomNalogu.id !== undefined) {
            this.subscribeToSaveResponse(this.operacijeURadnomNaloguService.update(this.operacijeURadnomNalogu));
        } else {
            this.subscribeToSaveResponse(this.operacijeURadnomNaloguService.create(this.operacijeURadnomNalogu));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOperacijeURadnomNalogu>>) {
        result.subscribe(
            (res: HttpResponse<IOperacijeURadnomNalogu>) => this.onSaveSuccess(),
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

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackRadniNalogById(index: number, item: IRadniNalog) {
        return item.id;
    }
}
