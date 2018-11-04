import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPomocniMaterijal } from 'app/shared/model/pomocni-materijal.model';
import { PomocniMaterijalService } from './pomocni-materijal.service';
import { IZahvati } from 'app/shared/model/zahvati.model';
import { ZahvatiService } from 'app/entities/zahvati';

@Component({
    selector: 'jhi-pomocni-materijal-update',
    templateUrl: './pomocni-materijal-update.component.html'
})
export class PomocniMaterijalUpdateComponent implements OnInit {
    pomocniMaterijal: IPomocniMaterijal;
    isSaving: boolean;

    zahvatis: IZahvati[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private pomocniMaterijalService: PomocniMaterijalService,
        private zahvatiService: ZahvatiService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pomocniMaterijal }) => {
            this.pomocniMaterijal = pomocniMaterijal;
        });
        this.zahvatiService.query({ filter: 'pomocnimaterijal-is-null' }).subscribe(
            (res: HttpResponse<IZahvati[]>) => {
                if (!this.pomocniMaterijal.zahvati || !this.pomocniMaterijal.zahvati.id) {
                    this.zahvatis = res.body;
                } else {
                    this.zahvatiService.find(this.pomocniMaterijal.zahvati.id).subscribe(
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
        if (this.pomocniMaterijal.id !== undefined) {
            this.subscribeToSaveResponse(this.pomocniMaterijalService.update(this.pomocniMaterijal));
        } else {
            this.subscribeToSaveResponse(this.pomocniMaterijalService.create(this.pomocniMaterijal));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPomocniMaterijal>>) {
        result.subscribe((res: HttpResponse<IPomocniMaterijal>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackZahvatiById(index: number, item: IZahvati) {
        return item.id;
    }
}
