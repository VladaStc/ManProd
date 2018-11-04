import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IDobavljac } from 'app/shared/model/dobavljac.model';
import { DobavljacService } from './dobavljac.service';
import { IAlati } from 'app/shared/model/alati.model';
import { AlatiService } from 'app/entities/alati';
import { IPribori } from 'app/shared/model/pribori.model';
import { PriboriService } from 'app/entities/pribori';
import { IMerniAlati } from 'app/shared/model/merni-alati.model';
import { MerniAlatiService } from 'app/entities/merni-alati';

@Component({
    selector: 'jhi-dobavljac-update',
    templateUrl: './dobavljac-update.component.html'
})
export class DobavljacUpdateComponent implements OnInit {
    dobavljac: IDobavljac;
    isSaving: boolean;

    alatis: IAlati[];

    priboris: IPribori[];

    mernialatis: IMerniAlati[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private dobavljacService: DobavljacService,
        private alatiService: AlatiService,
        private priboriService: PriboriService,
        private merniAlatiService: MerniAlatiService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ dobavljac }) => {
            this.dobavljac = dobavljac;
        });
        this.alatiService.query({ filter: 'dobavljac-is-null' }).subscribe(
            (res: HttpResponse<IAlati[]>) => {
                if (!this.dobavljac.alati || !this.dobavljac.alati.id) {
                    this.alatis = res.body;
                } else {
                    this.alatiService.find(this.dobavljac.alati.id).subscribe(
                        (subRes: HttpResponse<IAlati>) => {
                            this.alatis = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.priboriService.query({ filter: 'dobavljac-is-null' }).subscribe(
            (res: HttpResponse<IPribori[]>) => {
                if (!this.dobavljac.pribori || !this.dobavljac.pribori.id) {
                    this.priboris = res.body;
                } else {
                    this.priboriService.find(this.dobavljac.pribori.id).subscribe(
                        (subRes: HttpResponse<IPribori>) => {
                            this.priboris = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.merniAlatiService.query({ filter: 'dobavljac-is-null' }).subscribe(
            (res: HttpResponse<IMerniAlati[]>) => {
                if (!this.dobavljac.merniAlati || !this.dobavljac.merniAlati.id) {
                    this.mernialatis = res.body;
                } else {
                    this.merniAlatiService.find(this.dobavljac.merniAlati.id).subscribe(
                        (subRes: HttpResponse<IMerniAlati>) => {
                            this.mernialatis = [subRes.body].concat(res.body);
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
        if (this.dobavljac.id !== undefined) {
            this.subscribeToSaveResponse(this.dobavljacService.update(this.dobavljac));
        } else {
            this.subscribeToSaveResponse(this.dobavljacService.create(this.dobavljac));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDobavljac>>) {
        result.subscribe((res: HttpResponse<IDobavljac>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackAlatiById(index: number, item: IAlati) {
        return item.id;
    }

    trackPriboriById(index: number, item: IPribori) {
        return item.id;
    }

    trackMerniAlatiById(index: number, item: IMerniAlati) {
        return item.id;
    }
}
