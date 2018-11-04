import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMerniAlati } from 'app/shared/model/merni-alati.model';
import { Principal } from 'app/core';
import { MerniAlatiService } from './merni-alati.service';

@Component({
    selector: 'jhi-merni-alati',
    templateUrl: './merni-alati.component.html'
})
export class MerniAlatiComponent implements OnInit, OnDestroy {
    merniAlatis: IMerniAlati[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private merniAlatiService: MerniAlatiService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.merniAlatiService.query().subscribe(
            (res: HttpResponse<IMerniAlati[]>) => {
                this.merniAlatis = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInMerniAlatis();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IMerniAlati) {
        return item.id;
    }

    registerChangeInMerniAlatis() {
        this.eventSubscriber = this.eventManager.subscribe('merniAlatiListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
