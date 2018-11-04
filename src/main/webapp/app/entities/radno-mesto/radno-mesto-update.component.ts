import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IRadnoMesto } from 'app/shared/model/radno-mesto.model';
import { RadnoMestoService } from './radno-mesto.service';
import { IRadnici } from 'app/shared/model/radnici.model';
import { RadniciService } from 'app/entities/radnici';
import { IOperacijeURadnomNalogu } from 'app/shared/model/operacije-u-radnom-nalogu.model';
import { OperacijeURadnomNaloguService } from 'app/entities/operacije-u-radnom-nalogu';
import { IOperacije } from 'app/shared/model/operacije.model';
import { OperacijeService } from 'app/entities/operacije';
import { IZahvati } from 'app/shared/model/zahvati.model';
import { ZahvatiService } from 'app/entities/zahvati';

@Component({
    selector: 'jhi-radno-mesto-update',
    templateUrl: './radno-mesto-update.component.html'
})
export class RadnoMestoUpdateComponent implements OnInit {
    radnoMesto: IRadnoMesto;
    isSaving: boolean;

    radnicis: IRadnici[];

    operacijeuradnomnalogus: IOperacijeURadnomNalogu[];

    operacijes: IOperacije[];

    zahvatis: IZahvati[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private radnoMestoService: RadnoMestoService,
        private radniciService: RadniciService,
        private operacijeURadnomNaloguService: OperacijeURadnomNaloguService,
        private operacijeService: OperacijeService,
        private zahvatiService: ZahvatiService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ radnoMesto }) => {
            this.radnoMesto = radnoMesto;
        });
        this.radniciService.query({ filter: 'radnomesto-is-null' }).subscribe(
            (res: HttpResponse<IRadnici[]>) => {
                if (!this.radnoMesto.radnici || !this.radnoMesto.radnici.id) {
                    this.radnicis = res.body;
                } else {
                    this.radniciService.find(this.radnoMesto.radnici.id).subscribe(
                        (subRes: HttpResponse<IRadnici>) => {
                            this.radnicis = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.operacijeURadnomNaloguService.query({ filter: 'radnomesto-is-null' }).subscribe(
            (res: HttpResponse<IOperacijeURadnomNalogu[]>) => {
                if (!this.radnoMesto.operacijeURadnomNalogu || !this.radnoMesto.operacijeURadnomNalogu.id) {
                    this.operacijeuradnomnalogus = res.body;
                } else {
                    this.operacijeURadnomNaloguService.find(this.radnoMesto.operacijeURadnomNalogu.id).subscribe(
                        (subRes: HttpResponse<IOperacijeURadnomNalogu>) => {
                            this.operacijeuradnomnalogus = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.operacijeService.query({ filter: 'radnomesto-is-null' }).subscribe(
            (res: HttpResponse<IOperacije[]>) => {
                if (!this.radnoMesto.operacije || !this.radnoMesto.operacije.id) {
                    this.operacijes = res.body;
                } else {
                    this.operacijeService.find(this.radnoMesto.operacije.id).subscribe(
                        (subRes: HttpResponse<IOperacije>) => {
                            this.operacijes = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.zahvatiService.query({ filter: 'radnomesto-is-null' }).subscribe(
            (res: HttpResponse<IZahvati[]>) => {
                if (!this.radnoMesto.zahvati || !this.radnoMesto.zahvati.id) {
                    this.zahvatis = res.body;
                } else {
                    this.zahvatiService.find(this.radnoMesto.zahvati.id).subscribe(
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
        if (this.radnoMesto.id !== undefined) {
            this.subscribeToSaveResponse(this.radnoMestoService.update(this.radnoMesto));
        } else {
            this.subscribeToSaveResponse(this.radnoMestoService.create(this.radnoMesto));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRadnoMesto>>) {
        result.subscribe((res: HttpResponse<IRadnoMesto>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRadniciById(index: number, item: IRadnici) {
        return item.id;
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
