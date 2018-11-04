import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRadnoMesto } from 'app/shared/model/radno-mesto.model';
import { Principal } from 'app/core';
import { RadnoMestoService } from './radno-mesto.service';

@Component({
    selector: 'jhi-radno-mesto',
    templateUrl: './radno-mesto.component.html'
})
export class RadnoMestoComponent implements OnInit, OnDestroy {
    radnoMestos: IRadnoMesto[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private radnoMestoService: RadnoMestoService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.radnoMestoService.query().subscribe(
            (res: HttpResponse<IRadnoMesto[]>) => {
                this.radnoMestos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRadnoMestos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRadnoMesto) {
        return item.id;
    }

    registerChangeInRadnoMestos() {
        this.eventSubscriber = this.eventManager.subscribe('radnoMestoListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
