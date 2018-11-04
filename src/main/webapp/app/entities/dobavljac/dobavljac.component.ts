import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDobavljac } from 'app/shared/model/dobavljac.model';
import { Principal } from 'app/core';
import { DobavljacService } from './dobavljac.service';

@Component({
    selector: 'jhi-dobavljac',
    templateUrl: './dobavljac.component.html'
})
export class DobavljacComponent implements OnInit, OnDestroy {
    dobavljacs: IDobavljac[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private dobavljacService: DobavljacService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.dobavljacService.query().subscribe(
            (res: HttpResponse<IDobavljac[]>) => {
                this.dobavljacs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDobavljacs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDobavljac) {
        return item.id;
    }

    registerChangeInDobavljacs() {
        this.eventSubscriber = this.eventManager.subscribe('dobavljacListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
