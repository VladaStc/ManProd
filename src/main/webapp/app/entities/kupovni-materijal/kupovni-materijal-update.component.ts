import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IKupovniMaterijal } from 'app/shared/model/kupovni-materijal.model';
import { KupovniMaterijalService } from './kupovni-materijal.service';

@Component({
    selector: 'jhi-kupovni-materijal-update',
    templateUrl: './kupovni-materijal-update.component.html'
})
export class KupovniMaterijalUpdateComponent implements OnInit {
    kupovniMaterijal: IKupovniMaterijal;
    isSaving: boolean;

    constructor(private kupovniMaterijalService: KupovniMaterijalService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ kupovniMaterijal }) => {
            this.kupovniMaterijal = kupovniMaterijal;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.kupovniMaterijal.id !== undefined) {
            this.subscribeToSaveResponse(this.kupovniMaterijalService.update(this.kupovniMaterijal));
        } else {
            this.subscribeToSaveResponse(this.kupovniMaterijalService.create(this.kupovniMaterijal));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IKupovniMaterijal>>) {
        result.subscribe((res: HttpResponse<IKupovniMaterijal>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
