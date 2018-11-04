import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IStavkeUMagacinu } from 'app/shared/model/stavke-u-magacinu.model';
import { Principal } from 'app/core';
import { StavkeUMagacinuService } from './stavke-u-magacinu.service';

@Component({
    selector: 'jhi-stavke-u-magacinu',
    templateUrl: './stavke-u-magacinu.component.html'
})
export class StavkeUMagacinuComponent implements OnInit, OnDestroy {
    stavkeUMagacinus: IStavkeUMagacinu[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private stavkeUMagacinuService: StavkeUMagacinuService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.stavkeUMagacinuService.query().subscribe(
            (res: HttpResponse<IStavkeUMagacinu[]>) => {
                this.stavkeUMagacinus = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInStavkeUMagacinus();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IStavkeUMagacinu) {
        return item.id;
    }

    registerChangeInStavkeUMagacinus() {
        this.eventSubscriber = this.eventManager.subscribe('stavkeUMagacinuListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
