import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPomocniProizvod } from 'app/shared/model/pomocni-proizvod.model';
import { PomocniProizvodService } from './pomocni-proizvod.service';

@Component({
    selector: 'jhi-pomocni-proizvod-update',
    templateUrl: './pomocni-proizvod-update.component.html'
})
export class PomocniProizvodUpdateComponent implements OnInit {
    pomocniProizvod: IPomocniProizvod;
    isSaving: boolean;

    constructor(private pomocniProizvodService: PomocniProizvodService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pomocniProizvod }) => {
            this.pomocniProizvod = pomocniProizvod;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.pomocniProizvod.id !== undefined) {
            this.subscribeToSaveResponse(this.pomocniProizvodService.update(this.pomocniProizvod));
        } else {
            this.subscribeToSaveResponse(this.pomocniProizvodService.create(this.pomocniProizvod));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPomocniProizvod>>) {
        result.subscribe((res: HttpResponse<IPomocniProizvod>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
