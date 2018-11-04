import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IOprema } from 'app/shared/model/oprema.model';
import { OpremaService } from './oprema.service';
import { IObradniSistem } from 'app/shared/model/obradni-sistem.model';
import { ObradniSistemService } from 'app/entities/obradni-sistem';

@Component({
    selector: 'jhi-oprema-update',
    templateUrl: './oprema-update.component.html'
})
export class OpremaUpdateComponent implements OnInit {
    oprema: IOprema;
    isSaving: boolean;

    obradnisistems: IObradniSistem[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private opremaService: OpremaService,
        private obradniSistemService: ObradniSistemService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ oprema }) => {
            this.oprema = oprema;
        });
        this.obradniSistemService.query().subscribe(
            (res: HttpResponse<IObradniSistem[]>) => {
                this.obradnisistems = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.oprema.id !== undefined) {
            this.subscribeToSaveResponse(this.opremaService.update(this.oprema));
        } else {
            this.subscribeToSaveResponse(this.opremaService.create(this.oprema));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOprema>>) {
        result.subscribe((res: HttpResponse<IOprema>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
