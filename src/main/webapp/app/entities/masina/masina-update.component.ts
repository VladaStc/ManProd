import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IMasina } from 'app/shared/model/masina.model';
import { MasinaService } from './masina.service';
import { IObradniSistem } from 'app/shared/model/obradni-sistem.model';
import { ObradniSistemService } from 'app/entities/obradni-sistem';
import { IOperacijeURadnomNalogu } from 'app/shared/model/operacije-u-radnom-nalogu.model';
import { OperacijeURadnomNaloguService } from 'app/entities/operacije-u-radnom-nalogu';
import { IOperacije } from 'app/shared/model/operacije.model';
import { OperacijeService } from 'app/entities/operacije';
import { IZahvati } from 'app/shared/model/zahvati.model';
import { ZahvatiService } from 'app/entities/zahvati';

@Component({
    selector: 'jhi-masina-update',
    templateUrl: './masina-update.component.html'
})
export class MasinaUpdateComponent implements OnInit {
    masina: IMasina;
    isSaving: boolean;

    obradnisistems: IObradniSistem[];

    operacijeuradnomnalogus: IOperacijeURadnomNalogu[];

    operacijes: IOperacije[];

    zahvatis: IZahvati[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private masinaService: MasinaService,
        private obradniSistemService: ObradniSistemService,
        private operacijeURadnomNaloguService: OperacijeURadnomNaloguService,
        private operacijeService: OperacijeService,
        private zahvatiService: ZahvatiService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ masina }) => {
            this.masina = masina;
        });
        this.obradniSistemService.query().subscribe(
            (res: HttpResponse<IObradniSistem[]>) => {
                this.obradnisistems = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.operacijeURadnomNaloguService.query({ filter: 'masina-is-null' }).subscribe(
            (res: HttpResponse<IOperacijeURadnomNalogu[]>) => {
                if (!this.masina.operacijeURadnomNalogu || !this.masina.operacijeURadnomNalogu.id) {
                    this.operacijeuradnomnalogus = res.body;
                } else {
                    this.operacijeURadnomNaloguService.find(this.masina.operacijeURadnomNalogu.id).subscribe(
                        (subRes: HttpResponse<IOperacijeURadnomNalogu>) => {
                            this.operacijeuradnomnalogus = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.operacijeService.query({ filter: 'masina-is-null' }).subscribe(
            (res: HttpResponse<IOperacije[]>) => {
                if (!this.masina.operacije || !this.masina.operacije.id) {
                    this.operacijes = res.body;
                } else {
                    this.operacijeService.find(this.masina.operacije.id).subscribe(
                        (subRes: HttpResponse<IOperacije>) => {
                            this.operacijes = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.zahvatiService.query({ filter: 'masina-is-null' }).subscribe(
            (res: HttpResponse<IZahvati[]>) => {
                if (!this.masina.zahvati || !this.masina.zahvati.id) {
                    this.zahvatis = res.body;
                } else {
                    this.zahvatiService.find(this.masina.zahvati.id).subscribe(
                        (subRes: HttpResponse<IZahvati>) => {
                            this.zahvatis = [subRes.body].concat(res.body);
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
        if (this.masina.id !== undefined) {
            this.subscribeToSaveResponse(this.masinaService.update(this.masina));
        } else {
            this.subscribeToSaveResponse(this.masinaService.create(this.masina));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMasina>>) {
        result.subscribe((res: HttpResponse<IMasina>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackObradniSistemById(index: number, item: IObradniSistem) {
        return item.id;
    }

    trackOperacijeURadnomNaloguById(index: number, item: IOperacijeURadnomNalogu) {
        return item.id;
    }

    trackOperacijeById(index: number, item: IOperacije) {
        return item.id;
    }

    trackZahvatiById(index: number, item: IZahvati) {
        return item.id;
    }
}
