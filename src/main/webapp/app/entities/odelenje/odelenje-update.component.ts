import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IOdelenje } from 'app/shared/model/odelenje.model';
import { OdelenjeService } from './odelenje.service';
import { IRadionica } from 'app/shared/model/radionica.model';
import { RadionicaService } from 'app/entities/radionica';
import { IObradniSistem } from 'app/shared/model/obradni-sistem.model';
import { ObradniSistemService } from 'app/entities/obradni-sistem';

@Component({
    selector: 'jhi-odelenje-update',
    templateUrl: './odelenje-update.component.html'
})
export class OdelenjeUpdateComponent implements OnInit {
    odelenje: IOdelenje;
    isSaving: boolean;

    radionicas: IRadionica[];

    obradnisistems: IObradniSistem[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private odelenjeService: OdelenjeService,
        private radionicaService: RadionicaService,
        private obradniSistemService: ObradniSistemService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ odelenje }) => {
            this.odelenje = odelenje;
        });
        this.radionicaService.query({ filter: 'odelenje-is-null' }).subscribe(
            (res: HttpResponse<IRadionica[]>) => {
                if (!this.odelenje.radionica || !this.odelenje.radionica.id) {
                    this.radionicas = res.body;
                } else {
                    this.radionicaService.find(this.odelenje.radionica.id).subscribe(
                        (subRes: HttpResponse<IRadionica>) => {
                            this.radionicas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.obradniSistemService.query({ filter: 'odelenje-is-null' }).subscribe(
            (res: HttpResponse<IObradniSistem[]>) => {
                if (!this.odelenje.obradniSistem || !this.odelenje.obradniSistem.id) {
                    this.obradnisistems = res.body;
                } else {
                    this.obradniSistemService.find(this.odelenje.obradniSistem.id).subscribe(
                        (subRes: HttpResponse<IObradniSistem>) => {
                            this.obradnisistems = [subRes.body].concat(res.body);
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
        if (this.odelenje.id !== undefined) {
            this.subscribeToSaveResponse(this.odelenjeService.update(this.odelenje));
        } else {
            this.subscribeToSaveResponse(this.odelenjeService.create(this.odelenje));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOdelenje>>) {
        result.subscribe((res: HttpResponse<IOdelenje>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRadionicaById(index: number, item: IRadionica) {
        return item.id;
    }

    trackObradniSistemById(index: number, item: IObradniSistem) {
        return item.id;
    }
}
