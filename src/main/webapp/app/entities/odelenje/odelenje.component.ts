import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IOdelenje } from 'app/shared/model/odelenje.model';
import { Principal } from 'app/core';
import { OdelenjeService } from './odelenje.service';

@Component({
    selector: 'jhi-odelenje',
    templateUrl: './odelenje.component.html'
})
export class OdelenjeComponent implements OnInit, OnDestroy {
    odelenjes: IOdelenje[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private odelenjeService: OdelenjeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.odelenjeService.query().subscribe(
            (res: HttpResponse<IOdelenje[]>) => {
                this.odelenjes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInOdelenjes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IOdelenje) {
        return item.id;
    }

    registerChangeInOdelenjes() {
        this.eventSubscriber = this.eventManager.subscribe('odelenjeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
