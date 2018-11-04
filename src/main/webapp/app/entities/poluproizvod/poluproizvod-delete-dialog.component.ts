import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPoluproizvod } from 'app/shared/model/poluproizvod.model';
import { PoluproizvodService } from './poluproizvod.service';

@Component({
    selector: 'jhi-poluproizvod-delete-dialog',
    templateUrl: './poluproizvod-delete-dialog.component.html'
})
export class PoluproizvodDeleteDialogComponent {
    poluproizvod: IPoluproizvod;

    constructor(
        private poluproizvodService: PoluproizvodService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.poluproizvodService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'poluproizvodListModification',
                content: 'Deleted an poluproizvod'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-poluproizvod-delete-popup',
    template: ''
})
export class PoluproizvodDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ poluproizvod }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PoluproizvodDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.poluproizvod = poluproizvod;
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
