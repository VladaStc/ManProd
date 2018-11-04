import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IFinalniProizvod } from 'app/shared/model/finalni-proizvod.model';
import { FinalniProizvodService } from './finalni-proizvod.service';

@Component({
    selector: 'jhi-finalni-proizvod-update',
    templateUrl: './finalni-proizvod-update.component.html'
})
export class FinalniProizvodUpdateComponent implements OnInit {
    finalniProizvod: IFinalniProizvod;
    isSaving: boolean;

    constructor(private finalniProizvodService: FinalniProizvodService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ finalniProizvod }) => {
            this.finalniProizvod = finalniProizvod;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.finalniProizvod.id !== undefined) {
            this.subscribeToSaveResponse(this.finalniProizvodService.update(this.finalniProizvod));
        } else {
            this.subscribeToSaveResponse(this.finalniProizvodService.create(this.finalniProizvod));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFinalniProizvod>>) {
        result.subscribe((res: HttpResponse<IFinalniProizvod>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
