import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IProizvodniPogoni } from 'app/shared/model/proizvodni-pogoni.model';
import { ProizvodniPogoniService } from './proizvodni-pogoni.service';
import { IProizvodneLinije } from 'app/shared/model/proizvodne-linije.model';
import { ProizvodneLinijeService } from 'app/entities/proizvodne-linije';
import { IObradniSistem } from 'app/shared/model/obradni-sistem.model';
import { ObradniSistemService } from 'app/entities/obradni-sistem';

@Component({
    selector: 'jhi-proizvodni-pogoni-update',
    templateUrl: './proizvodni-pogoni-update.component.html'
})
export class ProizvodniPogoniUpdateComponent implements OnInit {
    proizvodniPogoni: IProizvodniPogoni;
    isSaving: boolean;

    proizvodnelinijes: IProizvodneLinije[];

    obradnisistems: IObradniSistem[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private proizvodniPogoniService: ProizvodniPogoniService,
        private proizvodneLinijeService: ProizvodneLinijeService,
        private obradniSistemService: ObradniSistemService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ proizvodniPogoni }) => {
            this.proizvodniPogoni = proizvodniPogoni;
        });
        this.proizvodneLinijeService.query({ filter: 'proizvodnipogoni-is-null' }).subscribe(
            (res: HttpResponse<IProizvodneLinije[]>) => {
                if (!this.proizvodniPogoni.proizvodneLinije || !this.proizvodniPogoni.proizvodneLinije.id) {
                    this.proizvodnelinijes = res.body;
                } else {
                    this.proizvodneLinijeService.find(this.proizvodniPogoni.proizvodneLinije.id).subscribe(
                        (subRes: HttpResponse<IProizvodneLinije>) => {
                            this.proizvodnelinijes = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.obradniSistemService.query({ filter: 'proizvodnipogoni-is-null' }).subscribe(
            (res: HttpResponse<IObradniSistem[]>) => {
                if (!this.proizvodniPogoni.obradniSistem || !this.proizvodniPogoni.obradniSistem.id) {
                    this.obradnisistems = res.body;
                } else {
                    this.obradniSistemService.find(this.proizvodniPogoni.obradniSistem.id).subscribe(
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
        if (this.proizvodniPogoni.id !== undefined) {
            this.subscribeToSaveResponse(this.proizvodniPogoniService.update(this.proizvodniPogoni));
        } else {
            this.subscribeToSaveResponse(this.proizvodniPogoniService.create(this.proizvodniPogoni));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProizvodniPogoni>>) {
        result.subscribe((res: HttpResponse<IProizvodniPogoni>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackProizvodneLinijeById(index: number, item: IProizvodneLinije) {
        return item.id;
    }

    trackObradniSistemById(index: number, item: IObradniSistem) {
        return item.id;
    }
}
