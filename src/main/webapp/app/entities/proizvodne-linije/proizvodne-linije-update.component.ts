import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IProizvodneLinije } from 'app/shared/model/proizvodne-linije.model';
import { ProizvodneLinijeService } from './proizvodne-linije.service';
import { IOdelenje } from 'app/shared/model/odelenje.model';
import { OdelenjeService } from 'app/entities/odelenje';
import { IObradniSistem } from 'app/shared/model/obradni-sistem.model';
import { ObradniSistemService } from 'app/entities/obradni-sistem';

@Component({
    selector: 'jhi-proizvodne-linije-update',
    templateUrl: './proizvodne-linije-update.component.html'
})
export class ProizvodneLinijeUpdateComponent implements OnInit {
    proizvodneLinije: IProizvodneLinije;
    isSaving: boolean;

    odelenjes: IOdelenje[];

    obradnisistems: IObradniSistem[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private proizvodneLinijeService: ProizvodneLinijeService,
        private odelenjeService: OdelenjeService,
        private obradniSistemService: ObradniSistemService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ proizvodneLinije }) => {
            this.proizvodneLinije = proizvodneLinije;
        });
        this.odelenjeService.query({ filter: 'proizvodnelinije-is-null' }).subscribe(
            (res: HttpResponse<IOdelenje[]>) => {
                if (!this.proizvodneLinije.odelenje || !this.proizvodneLinije.odelenje.id) {
                    this.odelenjes = res.body;
                } else {
                    this.odelenjeService.find(this.proizvodneLinije.odelenje.id).subscribe(
                        (subRes: HttpResponse<IOdelenje>) => {
                            this.odelenjes = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.obradniSistemService.query({ filter: 'proizvodnelinije-is-null' }).subscribe(
            (res: HttpResponse<IObradniSistem[]>) => {
                if (!this.proizvodneLinije.obradniSistem || !this.proizvodneLinije.obradniSistem.id) {
                    this.obradnisistems = res.body;
                } else {
                    this.obradniSistemService.find(this.proizvodneLinije.obradniSistem.id).subscribe(
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
        if (this.proizvodneLinije.id !== undefined) {
            this.subscribeToSaveResponse(this.proizvodneLinijeService.update(this.proizvodneLinije));
        } else {
            this.subscribeToSaveResponse(this.proizvodneLinijeService.create(this.proizvodneLinije));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProizvodneLinije>>) {
        result.subscribe((res: HttpResponse<IProizvodneLinije>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackOdelenjeById(index: number, item: IOdelenje) {
        return item.id;
    }

    trackObradniSistemById(index: number, item: IObradniSistem) {
        return item.id;
    }
}
