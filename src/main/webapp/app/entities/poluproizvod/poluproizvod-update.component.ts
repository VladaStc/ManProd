import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPoluproizvod } from 'app/shared/model/poluproizvod.model';
import { PoluproizvodService } from './poluproizvod.service';

@Component({
    selector: 'jhi-poluproizvod-update',
    templateUrl: './poluproizvod-update.component.html'
})
export class PoluproizvodUpdateComponent implements OnInit {
    poluproizvod: IPoluproizvod;
    isSaving: boolean;

    constructor(private poluproizvodService: PoluproizvodService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ poluproizvod }) => {
            this.poluproizvod = poluproizvod;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.poluproizvod.id !== undefined) {
            this.subscribeToSaveResponse(this.poluproizvodService.update(this.poluproizvod));
        } else {
            this.subscribeToSaveResponse(this.poluproizvodService.create(this.poluproizvod));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPoluproizvod>>) {
        result.subscribe((res: HttpResponse<IPoluproizvod>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
