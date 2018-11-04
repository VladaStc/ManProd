import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IZahvati } from 'app/shared/model/zahvati.model';
import { ZahvatiService } from './zahvati.service';

@Component({
    selector: 'jhi-zahvati-update',
    templateUrl: './zahvati-update.component.html'
})
export class ZahvatiUpdateComponent implements OnInit {
    zahvati: IZahvati;
    isSaving: boolean;

    constructor(private zahvatiService: ZahvatiService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ zahvati }) => {
            this.zahvati = zahvati;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.zahvati.id !== undefined) {
            this.subscribeToSaveResponse(this.zahvatiService.update(this.zahvati));
        } else {
            this.subscribeToSaveResponse(this.zahvatiService.create(this.zahvati));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IZahvati>>) {
        result.subscribe((res: HttpResponse<IZahvati>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
