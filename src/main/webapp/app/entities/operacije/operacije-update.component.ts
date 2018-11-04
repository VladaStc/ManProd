import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IOperacije } from 'app/shared/model/operacije.model';
import { OperacijeService } from './operacije.service';
import { IZahvati } from 'app/shared/model/zahvati.model';
import { ZahvatiService } from 'app/entities/zahvati';

@Component({
    selector: 'jhi-operacije-update',
    templateUrl: './operacije-update.component.html'
})
export class OperacijeUpdateComponent implements OnInit {
    operacije: IOperacije;
    isSaving: boolean;

    zahvatis: IZahvati[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private operacijeService: OperacijeService,
        private zahvatiService: ZahvatiService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ operacije }) => {
            this.operacije = operacije;
        });
        this.zahvatiService.query({ filter: 'operacije-is-null' }).subscribe(
            (res: HttpResponse<IZahvati[]>) => {
                if (!this.operacije.zahvati || !this.operacije.zahvati.id) {
                    this.zahvatis = res.body;
                } else {
                    this.zahvatiService.find(this.operacije.zahvati.id).subscribe(
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
        if (this.operacije.id !== undefined) {
            this.subscribeToSaveResponse(this.operacijeService.update(this.operacije));
        } else {
            this.subscribeToSaveResponse(this.operacijeService.create(this.operacije));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOperacije>>) {
        result.subscribe((res: HttpResponse<IOperacije>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
