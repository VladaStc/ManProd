import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IRadnici } from 'app/shared/model/radnici.model';
import { RadniciService } from './radnici.service';
import { IOperacijeURadnomNalogu } from 'app/shared/model/operacije-u-radnom-nalogu.model';
import { OperacijeURadnomNaloguService } from 'app/entities/operacije-u-radnom-nalogu';
import { IOperacije } from 'app/shared/model/operacije.model';
import { OperacijeService } from 'app/entities/operacije';
import { IZahvati } from 'app/shared/model/zahvati.model';
import { ZahvatiService } from 'app/entities/zahvati';

@Component({
    selector: 'jhi-radnici-update',
    templateUrl: './radnici-update.component.html'
})
export class RadniciUpdateComponent implements OnInit {
    radnici: IRadnici;
    isSaving: boolean;

    operacijeuradnomnalogus: IOperacijeURadnomNalogu[];

    operacijes: IOperacije[];

    zahvatis: IZahvati[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private radniciService: RadniciService,
        private operacijeURadnomNaloguService: OperacijeURadnomNaloguService,
        private operacijeService: OperacijeService,
        private zahvatiService: ZahvatiService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ radnici }) => {
            this.radnici = radnici;
        });
        this.operacijeURadnomNaloguService.query({ filter: 'radnici-is-null' }).subscribe(
            (res: HttpResponse<IOperacijeURadnomNalogu[]>) => {
                if (!this.radnici.operacijeURadnomNalogu || !this.radnici.operacijeURadnomNalogu.id) {
                    this.operacijeuradnomnalogus = res.body;
                } else {
                    this.operacijeURadnomNaloguService.find(this.radnici.operacijeURadnomNalogu.id).subscribe(
                        (subRes: HttpResponse<IOperacijeURadnomNalogu>) => {
                            this.operacijeuradnomnalogus = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.operacijeService.query({ filter: 'radnici-is-null' }).subscribe(
            (res: HttpResponse<IOperacije[]>) => {
                if (!this.radnici.operacije || !this.radnici.operacije.id) {
                    this.operacijes = res.body;
                } else {
                    this.operacijeService.find(this.radnici.operacije.id).subscribe(
                        (subRes: HttpResponse<IOperacije>) => {
                            this.operacijes = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.zahvatiService.query({ filter: 'radnici-is-null' }).subscribe(
            (res: HttpResponse<IZahvati[]>) => {
                if (!this.radnici.zahvati || !this.radnici.zahvati.id) {
                    this.zahvatis = res.body;
                } else {
                    this.zahvatiService.find(this.radnici.zahvati.id).subscribe(
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
        if (this.radnici.id !== undefined) {
            this.subscribeToSaveResponse(this.radniciService.update(this.radnici));
        } else {
            this.subscribeToSaveResponse(this.radniciService.create(this.radnici));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRadnici>>) {
        result.subscribe((res: HttpResponse<IRadnici>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackOperacijeURadnomNaloguById(index: number, item: IOperacijeURadnomNalogu) {
        return item.id;
    }

    trackOperacijeById(index: number, item: IOperacije) {
        return item.id;
    }

    trackZahvatiById(index: number, item: IZahvati) {
        return item.id;
    }
}
