import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IRadionica } from 'app/shared/model/radionica.model';
import { RadionicaService } from './radionica.service';
import { IRadnoMesto } from 'app/shared/model/radno-mesto.model';
import { RadnoMestoService } from 'app/entities/radno-mesto';

@Component({
    selector: 'jhi-radionica-update',
    templateUrl: './radionica-update.component.html'
})
export class RadionicaUpdateComponent implements OnInit {
    radionica: IRadionica;
    isSaving: boolean;

    radnomestos: IRadnoMesto[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private radionicaService: RadionicaService,
        private radnoMestoService: RadnoMestoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ radionica }) => {
            this.radionica = radionica;
        });
        this.radnoMestoService.query({ filter: 'radionica-is-null' }).subscribe(
            (res: HttpResponse<IRadnoMesto[]>) => {
                if (!this.radionica.radnoMesto || !this.radionica.radnoMesto.id) {
                    this.radnomestos = res.body;
                } else {
                    this.radnoMestoService.find(this.radionica.radnoMesto.id).subscribe(
                        (subRes: HttpResponse<IRadnoMesto>) => {
                            this.radnomestos = [subRes.body].concat(res.body);
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
        if (this.radionica.id !== undefined) {
            this.subscribeToSaveResponse(this.radionicaService.update(this.radionica));
        } else {
            this.subscribeToSaveResponse(this.radionicaService.create(this.radionica));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRadionica>>) {
        result.subscribe((res: HttpResponse<IRadionica>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRadnoMestoById(index: number, item: IRadnoMesto) {
        return item.id;
    }
}
