import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IOstaliMaterijali } from 'app/shared/model/ostali-materijali.model';
import { OstaliMaterijaliService } from './ostali-materijali.service';

@Component({
    selector: 'jhi-ostali-materijali-update',
    templateUrl: './ostali-materijali-update.component.html'
})
export class OstaliMaterijaliUpdateComponent implements OnInit {
    ostaliMaterijali: IOstaliMaterijali;
    isSaving: boolean;

    constructor(private ostaliMaterijaliService: OstaliMaterijaliService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ ostaliMaterijali }) => {
            this.ostaliMaterijali = ostaliMaterijali;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.ostaliMaterijali.id !== undefined) {
            this.subscribeToSaveResponse(this.ostaliMaterijaliService.update(this.ostaliMaterijali));
        } else {
            this.subscribeToSaveResponse(this.ostaliMaterijaliService.create(this.ostaliMaterijali));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOstaliMaterijali>>) {
        result.subscribe((res: HttpResponse<IOstaliMaterijali>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
