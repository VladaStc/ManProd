import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPotrosniMaterijal } from 'app/shared/model/potrosni-materijal.model';
import { PotrosniMaterijalService } from './potrosni-materijal.service';

@Component({
    selector: 'jhi-potrosni-materijal-update',
    templateUrl: './potrosni-materijal-update.component.html'
})
export class PotrosniMaterijalUpdateComponent implements OnInit {
    potrosniMaterijal: IPotrosniMaterijal;
    isSaving: boolean;

    constructor(private potrosniMaterijalService: PotrosniMaterijalService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ potrosniMaterijal }) => {
            this.potrosniMaterijal = potrosniMaterijal;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.potrosniMaterijal.id !== undefined) {
            this.subscribeToSaveResponse(this.potrosniMaterijalService.update(this.potrosniMaterijal));
        } else {
            this.subscribeToSaveResponse(this.potrosniMaterijalService.create(this.potrosniMaterijal));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPotrosniMaterijal>>) {
        result.subscribe((res: HttpResponse<IPotrosniMaterijal>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
