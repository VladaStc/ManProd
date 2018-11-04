import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IProizvodneLinije } from 'app/shared/model/proizvodne-linije.model';
import { Principal } from 'app/core';
import { ProizvodneLinijeService } from './proizvodne-linije.service';

@Component({
    selector: 'jhi-proizvodne-linije',
    templateUrl: './proizvodne-linije.component.html'
})
export class ProizvodneLinijeComponent implements OnInit, OnDestroy {
    proizvodneLinijes: IProizvodneLinije[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private proizvodneLinijeService: ProizvodneLinijeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.proizvodneLinijeService.query().subscribe(
            (res: HttpResponse<IProizvodneLinije[]>) => {
                this.proizvodneLinijes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInProizvodneLinijes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IProizvodneLinije) {
        return item.id;
    }

    registerChangeInProizvodneLinijes() {
        this.eventSubscriber = this.eventManager.subscribe('proizvodneLinijeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
