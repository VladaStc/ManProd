import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISirovine } from 'app/shared/model/sirovine.model';
import { SirovineService } from './sirovine.service';
import { IOperacijeURadnomNalogu } from 'app/shared/model/operacije-u-radnom-nalogu.model';
import { OperacijeURadnomNaloguService } from 'app/entities/operacije-u-radnom-nalogu';
import { IOperacije } from 'app/shared/model/operacije.model';
import { OperacijeService } from 'app/entities/operacije';
import { IZahvati } from 'app/shared/model/zahvati.model';
import { ZahvatiService } from 'app/entities/zahvati';

@Component({
    selector: 'jhi-sirovine-update',
    templateUrl: './sirovine-update.component.html'
})
export class SirovineUpdateComponent implements OnInit {
    sirovine: ISirovine;
    isSaving: boolean;

    operacijeuradnomnalogus: IOperacijeURadnomNalogu[];

    operacijes: IOperacije[];

    zahvatis: IZahvati[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private sirovineService: SirovineService,
        private operacijeURadnomNaloguService: OperacijeURadnomNaloguService,
        private operacijeService: OperacijeService,
        private zahvatiService: ZahvatiService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sirovine }) => {
            this.sirovine = sirovine;
        });
        this.operacijeURadnomNaloguService.query({ filter: 'sirovine-is-null' }).subscribe(
            (res: HttpResponse<IOperacijeURadnomNalogu[]>) => {
                if (!this.sirovine.operacijeURadnomNalogu || !this.sirovine.operacijeURadnomNalogu.id) {
                    this.operacijeuradnomnalogus = res.body;
                } else {
                    this.operacijeURadnomNaloguService.find(this.sirovine.operacijeURadnomNalogu.id).subscribe(
                        (subRes: HttpResponse<IOperacijeURadnomNalogu>) => {
                            this.operacijeuradnomnalogus = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.operacijeService.query({ filter: 'sirovine-is-null' }).subscribe(
            (res: HttpResponse<IOperacije[]>) => {
                if (!this.sirovine.operacije || !this.sirovine.operacije.id) {
                    this.operacijes = res.body;
                } else {
                    this.operacijeService.find(this.sirovine.operacije.id).subscribe(
                        (subRes: HttpResponse<IOperacije>) => {
                            this.operacijes = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.zahvatiService.query({ filter: 'sirovine-is-null' }).subscribe(
            (res: HttpResponse<IZahvati[]>) => {
                if (!this.sirovine.zahvati || !this.sirovine.zahvati.id) {
                    this.zahvatis = res.body;
                } else {
                    this.zahvatiService.find(this.sirovine.zahvati.id).subscribe(
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
        if (this.sirovine.id !== undefined) {
            this.subscribeToSaveResponse(this.sirovineService.update(this.sirovine));
        } else {
            this.subscribeToSaveResponse(this.sirovineService.create(this.sirovine));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISirovine>>) {
        result.subscribe((res: HttpResponse<ISirovine>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
