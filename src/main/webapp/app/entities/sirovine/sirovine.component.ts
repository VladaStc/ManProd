import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISirovine } from 'app/shared/model/sirovine.model';
import { Principal } from 'app/core';
import { SirovineService } from './sirovine.service';

@Component({
    selector: 'jhi-sirovine',
    templateUrl: './sirovine.component.html'
})
export class SirovineComponent implements OnInit, OnDestroy {
    sirovines: ISirovine[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private sirovineService: SirovineService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.sirovineService.query().subscribe(
            (res: HttpResponse<ISirovine[]>) => {
                this.sirovines = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSirovines();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISirovine) {
        return item.id;
    }

    registerChangeInSirovines() {
        this.eventSubscriber = this.eventManager.subscribe('sirovineListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
