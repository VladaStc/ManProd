import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPribori } from 'app/shared/model/pribori.model';
import { Principal } from 'app/core';
import { PriboriService } from './pribori.service';

@Component({
    selector: 'jhi-pribori',
    templateUrl: './pribori.component.html'
})
export class PriboriComponent implements OnInit, OnDestroy {
    priboris: IPribori[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private priboriService: PriboriService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.priboriService.query().subscribe(
            (res: HttpResponse<IPribori[]>) => {
                this.priboris = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPriboris();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPribori) {
        return item.id;
    }

    registerChangeInPriboris() {
        this.eventSubscriber = this.eventManager.subscribe('priboriListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
