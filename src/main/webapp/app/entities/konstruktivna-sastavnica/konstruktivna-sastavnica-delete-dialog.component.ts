import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IKonstruktivnaSastavnica } from 'app/shared/model/konstruktivna-sastavnica.model';
import { KonstruktivnaSastavnicaService } from './konstruktivna-sastavnica.service';

@Component({
    selector: 'jhi-konstruktivna-sastavnica-delete-dialog',
    templateUrl: './konstruktivna-sastavnica-delete-dialog.component.html'
})
export class KonstruktivnaSastavnicaDeleteDialogComponent {
    konstruktivnaSastavnica: IKonstruktivnaSastavnica;

    constructor(
        private konstruktivnaSastavnicaService: KonstruktivnaSastavnicaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.konstruktivnaSastavnicaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'konstruktivnaSastavnicaListModification',
                content: 'Deleted an konstruktivnaSastavnica'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-konstruktivna-sastavnica-delete-popup',
    template: ''
})
export class KonstruktivnaSastavnicaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ konstruktivnaSastavnica }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(KonstruktivnaSastavnicaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.konstruktivnaSastavnica = konstruktivnaSastavnica;
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
