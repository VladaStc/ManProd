import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IOstaliMaterijali } from 'app/shared/model/ostali-materijali.model';
import { Principal } from 'app/core';
import { OstaliMaterijaliService } from './ostali-materijali.service';

@Component({
    selector: 'jhi-ostali-materijali',
    templateUrl: './ostali-materijali.component.html'
})
export class OstaliMaterijaliComponent implements OnInit, OnDestroy {
    ostaliMaterijalis: IOstaliMaterijali[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private ostaliMaterijaliService: OstaliMaterijaliService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.ostaliMaterijaliService.query().subscribe(
            (res: HttpResponse<IOstaliMaterijali[]>) => {
                this.ostaliMaterijalis = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInOstaliMaterijalis();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IOstaliMaterijali) {
        return item.id;
    }

    registerChangeInOstaliMaterijalis() {
        this.eventSubscriber = this.eventManager.subscribe('ostaliMaterijaliListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
