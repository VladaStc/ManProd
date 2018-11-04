import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IKupovniMaterijal } from 'app/shared/model/kupovni-materijal.model';
import { Principal } from 'app/core';
import { KupovniMaterijalService } from './kupovni-materijal.service';

@Component({
    selector: 'jhi-kupovni-materijal',
    templateUrl: './kupovni-materijal.component.html'
})
export class KupovniMaterijalComponent implements OnInit, OnDestroy {
    kupovniMaterijals: IKupovniMaterijal[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private kupovniMaterijalService: KupovniMaterijalService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.kupovniMaterijalService.query().subscribe(
            (res: HttpResponse<IKupovniMaterijal[]>) => {
                this.kupovniMaterijals = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInKupovniMaterijals();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IKupovniMaterijal) {
        return item.id;
    }

    registerChangeInKupovniMaterijals() {
        this.eventSubscriber = this.eventManager.subscribe('kupovniMaterijalListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
