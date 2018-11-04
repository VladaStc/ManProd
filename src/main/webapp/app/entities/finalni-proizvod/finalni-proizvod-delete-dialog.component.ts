import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFinalniProizvod } from 'app/shared/model/finalni-proizvod.model';
import { FinalniProizvodService } from './finalni-proizvod.service';

@Component({
    selector: 'jhi-finalni-proizvod-delete-dialog',
    templateUrl: './finalni-proizvod-delete-dialog.component.html'
})
export class FinalniProizvodDeleteDialogComponent {
    finalniProizvod: IFinalniProizvod;

    constructor(
        private finalniProizvodService: FinalniProizvodService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.finalniProizvodService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'finalniProizvodListModification',
                content: 'Deleted an finalniProizvod'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-finalni-proizvod-delete-popup',
    template: ''
})
export class FinalniProizvodDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ finalniProizvod }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FinalniProizvodDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.finalniProizvod = finalniProizvod;
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
