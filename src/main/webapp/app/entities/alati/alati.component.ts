import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAlati } from 'app/shared/model/alati.model';
import { Principal } from 'app/core';
import { AlatiService } from './alati.service';

@Component({
    selector: 'jhi-alati',
    templateUrl: './alati.component.html'
})
export class AlatiComponent implements OnInit, OnDestroy {
    alatis: IAlati[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private alatiService: AlatiService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.alatiService.query().subscribe(
            (res: HttpResponse<IAlati[]>) => {
                this.alatis = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAlatis();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAlati) {
        return item.id;
    }

    registerChangeInAlatis() {
        this.eventSubscriber = this.eventManager.subscribe('alatiListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
