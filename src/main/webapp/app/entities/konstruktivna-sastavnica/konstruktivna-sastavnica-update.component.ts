import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IKonstruktivnaSastavnica } from 'app/shared/model/konstruktivna-sastavnica.model';
import { KonstruktivnaSastavnicaService } from './konstruktivna-sastavnica.service';

@Component({
    selector: 'jhi-konstruktivna-sastavnica-update',
    templateUrl: './konstruktivna-sastavnica-update.component.html'
})
export class KonstruktivnaSastavnicaUpdateComponent implements OnInit {
    konstruktivnaSastavnica: IKonstruktivnaSastavnica;
    isSaving: boolean;

    constructor(private konstruktivnaSastavnicaService: KonstruktivnaSastavnicaService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ konstruktivnaSastavnica }) => {
            this.konstruktivnaSastavnica = konstruktivnaSastavnica;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.konstruktivnaSastavnica.id !== undefined) {
            this.subscribeToSaveResponse(this.konstruktivnaSastavnicaService.update(this.konstruktivnaSastavnica));
        } else {
            this.subscribeToSaveResponse(this.konstruktivnaSastavnicaService.create(this.konstruktivnaSastavnica));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IKonstruktivnaSastavnica>>) {
        result.subscribe(
            (res: HttpResponse<IKonstruktivnaSastavnica>) => this.onSaveSuccess(),
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
}
