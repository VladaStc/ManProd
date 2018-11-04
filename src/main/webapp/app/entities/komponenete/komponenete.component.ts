import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IKomponenete } from 'app/shared/model/komponenete.model';
import { Principal } from 'app/core';
import { KomponeneteService } from './komponenete.service';

@Component({
    selector: 'jhi-komponenete',
    templateUrl: './komponenete.component.html'
})
export class KomponeneteComponent implements OnInit, OnDestroy {
    komponenetes: IKomponenete[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private komponeneteService: KomponeneteService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.komponeneteService.query().subscribe(
            (res: HttpResponse<IKomponenete[]>) => {
                this.komponenetes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInKomponenetes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IKomponenete) {
        return item.id;
    }

    registerChangeInKomponenetes() {
        this.eventSubscriber = this.eventManager.subscribe('komponeneteListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
