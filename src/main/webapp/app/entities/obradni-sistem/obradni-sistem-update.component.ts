import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IObradniSistem } from 'app/shared/model/obradni-sistem.model';
import { ObradniSistemService } from './obradni-sistem.service';
import { IKonstruktivnaSastavnica } from 'app/shared/model/konstruktivna-sastavnica.model';
import { KonstruktivnaSastavnicaService } from 'app/entities/konstruktivna-sastavnica';

@Component({
    selector: 'jhi-obradni-sistem-update',
    templateUrl: './obradni-sistem-update.component.html'
})
export class ObradniSistemUpdateComponent implements OnInit {
    obradniSistem: IObradniSistem;
    isSaving: boolean;

    konstruktivnasastavnicas: IKonstruktivnaSastavnica[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private obradniSistemService: ObradniSistemService,
        private konstruktivnaSastavnicaService: KonstruktivnaSastavnicaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ obradniSistem }) => {
            this.obradniSistem = obradniSistem;
        });
        this.konstruktivnaSastavnicaService.query({ filter: 'obradnisistem-is-null' }).subscribe(
            (res: HttpResponse<IKonstruktivnaSastavnica[]>) => {
                if (!this.obradniSistem.konstruktivnaSastavnica || !this.obradniSistem.konstruktivnaSastavnica.id) {
                    this.konstruktivnasastavnicas = res.body;
                } else {
                    this.konstruktivnaSastavnicaService.find(this.obradniSistem.konstruktivnaSastavnica.id).subscribe(
                        (subRes: HttpResponse<IKonstruktivnaSastavnica>) => {
                            this.konstruktivnasastavnicas = [subRes.body].concat(res.body);
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
        if (this.obradniSistem.id !== undefined) {
            this.subscribeToSaveResponse(this.obradniSistemService.update(this.obradniSistem));
        } else {
            this.subscribeToSaveResponse(this.obradniSistemService.create(this.obradniSistem));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IObradniSistem>>) {
        result.subscribe((res: HttpResponse<IObradniSistem>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackKonstruktivnaSastavnicaById(index: number, item: IKonstruktivnaSastavnica) {
        return item.id;
    }
}
