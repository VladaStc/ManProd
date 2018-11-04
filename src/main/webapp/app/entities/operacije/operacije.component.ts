import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IOperacije } from 'app/shared/model/operacije.model';
import { Principal } from 'app/core';
import { OperacijeService } from './operacije.service';

@Component({
    selector: 'jhi-operacije',
    templateUrl: './operacije.component.html'
})
export class OperacijeComponent implements OnInit, OnDestroy {
    operacijes: IOperacije[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private operacijeService: OperacijeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.operacijeService.query().subscribe(
            (res: HttpResponse<IOperacije[]>) => {
                this.operacijes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInOperacijes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IOperacije) {
        return item.id;
    }

    registerChangeInOperacijes() {
        this.eventSubscriber = this.eventManager.subscribe('operacijeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
