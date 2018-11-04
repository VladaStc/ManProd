import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IKupovniProizvod } from 'app/shared/model/kupovni-proizvod.model';
import { Principal } from 'app/core';
import { KupovniProizvodService } from './kupovni-proizvod.service';

@Component({
    selector: 'jhi-kupovni-proizvod',
    templateUrl: './kupovni-proizvod.component.html'
})
export class KupovniProizvodComponent implements OnInit, OnDestroy {
    kupovniProizvods: IKupovniProizvod[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private kupovniProizvodService: KupovniProizvodService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.kupovniProizvodService.query().subscribe(
            (res: HttpResponse<IKupovniProizvod[]>) => {
                this.kupovniProizvods = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInKupovniProizvods();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IKupovniProizvod) {
        return item.id;
    }

    registerChangeInKupovniProizvods() {
        this.eventSubscriber = this.eventManager.subscribe('kupovniProizvodListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
