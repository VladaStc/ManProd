import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IFinalniProizvod } from 'app/shared/model/finalni-proizvod.model';
import { Principal } from 'app/core';
import { FinalniProizvodService } from './finalni-proizvod.service';

@Component({
    selector: 'jhi-finalni-proizvod',
    templateUrl: './finalni-proizvod.component.html'
})
export class FinalniProizvodComponent implements OnInit, OnDestroy {
    finalniProizvods: IFinalniProizvod[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private finalniProizvodService: FinalniProizvodService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.finalniProizvodService.query().subscribe(
            (res: HttpResponse<IFinalniProizvod[]>) => {
                this.finalniProizvods = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInFinalniProizvods();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IFinalniProizvod) {
        return item.id;
    }

    registerChangeInFinalniProizvods() {
        this.eventSubscriber = this.eventManager.subscribe('finalniProizvodListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
