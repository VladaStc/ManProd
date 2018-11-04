import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IKupovniProizvod } from 'app/shared/model/kupovni-proizvod.model';
import { KupovniProizvodService } from './kupovni-proizvod.service';

@Component({
    selector: 'jhi-kupovni-proizvod-update',
    templateUrl: './kupovni-proizvod-update.component.html'
})
export class KupovniProizvodUpdateComponent implements OnInit {
    kupovniProizvod: IKupovniProizvod;
    isSaving: boolean;

    constructor(private kupovniProizvodService: KupovniProizvodService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ kupovniProizvod }) => {
            this.kupovniProizvod = kupovniProizvod;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.kupovniProizvod.id !== undefined) {
            this.subscribeToSaveResponse(this.kupovniProizvodService.update(this.kupovniProizvod));
        } else {
            this.subscribeToSaveResponse(this.kupovniProizvodService.create(this.kupovniProizvod));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IKupovniProizvod>>) {
        result.subscribe((res: HttpResponse<IKupovniProizvod>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
