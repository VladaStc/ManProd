import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IZahvati } from 'app/shared/model/zahvati.model';
import { Principal } from 'app/core';
import { ZahvatiService } from './zahvati.service';

@Component({
    selector: 'jhi-zahvati',
    templateUrl: './zahvati.component.html'
})
export class ZahvatiComponent implements OnInit, OnDestroy {
    zahvatis: IZahvati[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private zahvatiService: ZahvatiService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.zahvatiService.query().subscribe(
            (res: HttpResponse<IZahvati[]>) => {
                this.zahvatis = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInZahvatis();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IZahvati) {
        return item.id;
    }

    registerChangeInZahvatis() {
        this.eventSubscriber = this.eventManager.subscribe('zahvatiListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
