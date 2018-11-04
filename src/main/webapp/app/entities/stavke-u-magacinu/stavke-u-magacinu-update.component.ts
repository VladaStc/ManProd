import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IStavkeUMagacinu } from 'app/shared/model/stavke-u-magacinu.model';
import { StavkeUMagacinuService } from './stavke-u-magacinu.service';

@Component({
    selector: 'jhi-stavke-u-magacinu-update',
    templateUrl: './stavke-u-magacinu-update.component.html'
})
export class StavkeUMagacinuUpdateComponent implements OnInit {
    stavkeUMagacinu: IStavkeUMagacinu;
    isSaving: boolean;

    constructor(private stavkeUMagacinuService: StavkeUMagacinuService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ stavkeUMagacinu }) => {
            this.stavkeUMagacinu = stavkeUMagacinu;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.stavkeUMagacinu.id !== undefined) {
            this.subscribeToSaveResponse(this.stavkeUMagacinuService.update(this.stavkeUMagacinu));
        } else {
            this.subscribeToSaveResponse(this.stavkeUMagacinuService.create(this.stavkeUMagacinu));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IStavkeUMagacinu>>) {
        result.subscribe((res: HttpResponse<IStavkeUMagacinu>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
