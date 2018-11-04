import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITransakcijeUMagacinu } from 'app/shared/model/transakcije-u-magacinu.model';
import { Principal } from 'app/core';
import { TransakcijeUMagacinuService } from './transakcije-u-magacinu.service';

@Component({
    selector: 'jhi-transakcije-u-magacinu',
    templateUrl: './transakcije-u-magacinu.component.html'
})
export class TransakcijeUMagacinuComponent implements OnInit, OnDestroy {
    transakcijeUMagacinus: ITransakcijeUMagacinu[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private transakcijeUMagacinuService: TransakcijeUMagacinuService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.transakcijeUMagacinuService.query().subscribe(
            (res: HttpResponse<ITransakcijeUMagacinu[]>) => {
                this.transakcijeUMagacinus = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTransakcijeUMagacinus();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITransakcijeUMagacinu) {
        return item.id;
    }

    registerChangeInTransakcijeUMagacinus() {
        this.eventSubscriber = this.eventManager.subscribe('transakcijeUMagacinuListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
