import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRadionica } from 'app/shared/model/radionica.model';
import { Principal } from 'app/core';
import { RadionicaService } from './radionica.service';

@Component({
    selector: 'jhi-radionica',
    templateUrl: './radionica.component.html'
})
export class RadionicaComponent implements OnInit, OnDestroy {
    radionicas: IRadionica[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private radionicaService: RadionicaService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.radionicaService.query().subscribe(
            (res: HttpResponse<IRadionica[]>) => {
                this.radionicas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRadionicas();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRadionica) {
        return item.id;
    }

    registerChangeInRadionicas() {
        this.eventSubscriber = this.eventManager.subscribe('radionicaListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
