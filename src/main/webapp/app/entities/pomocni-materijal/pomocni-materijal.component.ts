import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPomocniMaterijal } from 'app/shared/model/pomocni-materijal.model';
import { Principal } from 'app/core';
import { PomocniMaterijalService } from './pomocni-materijal.service';

@Component({
    selector: 'jhi-pomocni-materijal',
    templateUrl: './pomocni-materijal.component.html'
})
export class PomocniMaterijalComponent implements OnInit, OnDestroy {
    pomocniMaterijals: IPomocniMaterijal[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private pomocniMaterijalService: PomocniMaterijalService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.pomocniMaterijalService.query().subscribe(
            (res: HttpResponse<IPomocniMaterijal[]>) => {
                this.pomocniMaterijals = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPomocniMaterijals();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPomocniMaterijal) {
        return item.id;
    }

    registerChangeInPomocniMaterijals() {
        this.eventSubscriber = this.eventManager.subscribe('pomocniMaterijalListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
