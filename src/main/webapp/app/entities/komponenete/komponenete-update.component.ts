import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IKomponenete } from 'app/shared/model/komponenete.model';
import { KomponeneteService } from './komponenete.service';

@Component({
    selector: 'jhi-komponenete-update',
    templateUrl: './komponenete-update.component.html'
})
export class KomponeneteUpdateComponent implements OnInit {
    komponenete: IKomponenete;
    isSaving: boolean;

    constructor(private komponeneteService: KomponeneteService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ komponenete }) => {
            this.komponenete = komponenete;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.komponenete.id !== undefined) {
            this.subscribeToSaveResponse(this.komponeneteService.update(this.komponenete));
        } else {
            this.subscribeToSaveResponse(this.komponeneteService.create(this.komponenete));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IKomponenete>>) {
        result.subscribe((res: HttpResponse<IKomponenete>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
