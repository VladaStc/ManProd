import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPoluproizvod } from 'app/shared/model/poluproizvod.model';
import { Principal } from 'app/core';
import { PoluproizvodService } from './poluproizvod.service';

@Component({
    selector: 'jhi-poluproizvod',
    templateUrl: './poluproizvod.component.html'
})
export class PoluproizvodComponent implements OnInit, OnDestroy {
    poluproizvods: IPoluproizvod[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private poluproizvodService: PoluproizvodService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.poluproizvodService.query().subscribe(
            (res: HttpResponse<IPoluproizvod[]>) => {
                this.poluproizvods = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPoluproizvods();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPoluproizvod) {
        return item.id;
    }

    registerChangeInPoluproizvods() {
        this.eventSubscriber = this.eventManager.subscribe('poluproizvodListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
