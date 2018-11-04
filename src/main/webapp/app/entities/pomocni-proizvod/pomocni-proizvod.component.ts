import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPomocniProizvod } from 'app/shared/model/pomocni-proizvod.model';
import { Principal } from 'app/core';
import { PomocniProizvodService } from './pomocni-proizvod.service';

@Component({
    selector: 'jhi-pomocni-proizvod',
    templateUrl: './pomocni-proizvod.component.html'
})
export class PomocniProizvodComponent implements OnInit, OnDestroy {
    pomocniProizvods: IPomocniProizvod[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private pomocniProizvodService: PomocniProizvodService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.pomocniProizvodService.query().subscribe(
            (res: HttpResponse<IPomocniProizvod[]>) => {
                this.pomocniProizvods = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPomocniProizvods();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPomocniProizvod) {
        return item.id;
    }

    registerChangeInPomocniProizvods() {
        this.eventSubscriber = this.eventManager.subscribe('pomocniProizvodListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
