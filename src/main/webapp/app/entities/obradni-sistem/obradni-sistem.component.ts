import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IObradniSistem } from 'app/shared/model/obradni-sistem.model';
import { Principal } from 'app/core';
import { ObradniSistemService } from './obradni-sistem.service';

@Component({
    selector: 'jhi-obradni-sistem',
    templateUrl: './obradni-sistem.component.html'
})
export class ObradniSistemComponent implements OnInit, OnDestroy {
    obradniSistems: IObradniSistem[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private obradniSistemService: ObradniSistemService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.obradniSistemService.query().subscribe(
            (res: HttpResponse<IObradniSistem[]>) => {
                this.obradniSistems = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInObradniSistems();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IObradniSistem) {
        return item.id;
    }

    registerChangeInObradniSistems() {
        this.eventSubscriber = this.eventManager.subscribe('obradniSistemListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
