import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IOprema } from 'app/shared/model/oprema.model';
import { Principal } from 'app/core';
import { OpremaService } from './oprema.service';

@Component({
    selector: 'jhi-oprema',
    templateUrl: './oprema.component.html'
})
export class OpremaComponent implements OnInit, OnDestroy {
    opremas: IOprema[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private opremaService: OpremaService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.opremaService.query().subscribe(
            (res: HttpResponse<IOprema[]>) => {
                this.opremas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInOpremas();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IOprema) {
        return item.id;
    }

    registerChangeInOpremas() {
        this.eventSubscriber = this.eventManager.subscribe('opremaListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
