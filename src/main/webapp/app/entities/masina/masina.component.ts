import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMasina } from 'app/shared/model/masina.model';
import { Principal } from 'app/core';
import { MasinaService } from './masina.service';

@Component({
    selector: 'jhi-masina',
    templateUrl: './masina.component.html'
})
export class MasinaComponent implements OnInit, OnDestroy {
    masinas: IMasina[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private masinaService: MasinaService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.masinaService.query().subscribe(
            (res: HttpResponse<IMasina[]>) => {
                this.masinas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInMasinas();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IMasina) {
        return item.id;
    }

    registerChangeInMasinas() {
        this.eventSubscriber = this.eventManager.subscribe('masinaListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
