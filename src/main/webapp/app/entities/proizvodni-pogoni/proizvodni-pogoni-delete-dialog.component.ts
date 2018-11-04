import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProizvodniPogoni } from 'app/shared/model/proizvodni-pogoni.model';
import { ProizvodniPogoniService } from './proizvodni-pogoni.service';

@Component({
    selector: 'jhi-proizvodni-pogoni-delete-dialog',
    templateUrl: './proizvodni-pogoni-delete-dialog.component.html'
})
export class ProizvodniPogoniDeleteDialogComponent {
    proizvodniPogoni: IProizvodniPogoni;

    constructor(
        private proizvodniPogoniService: ProizvodniPogoniService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.proizvodniPogoniService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'proizvodniPogoniListModification',
                content: 'Deleted an proizvodniPogoni'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-proizvodni-pogoni-delete-popup',
    template: ''
})
export class ProizvodniPogoniDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ proizvodniPogoni }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProizvodniPogoniDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.proizvodniPogoni = proizvodniPogoni;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
