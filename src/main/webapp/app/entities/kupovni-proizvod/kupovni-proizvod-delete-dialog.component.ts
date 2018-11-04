import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IKupovniProizvod } from 'app/shared/model/kupovni-proizvod.model';
import { KupovniProizvodService } from './kupovni-proizvod.service';

@Component({
    selector: 'jhi-kupovni-proizvod-delete-dialog',
    templateUrl: './kupovni-proizvod-delete-dialog.component.html'
})
export class KupovniProizvodDeleteDialogComponent {
    kupovniProizvod: IKupovniProizvod;

    constructor(
        private kupovniProizvodService: KupovniProizvodService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.kupovniProizvodService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'kupovniProizvodListModification',
                content: 'Deleted an kupovniProizvod'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-kupovni-proizvod-delete-popup',
    template: ''
})
export class KupovniProizvodDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ kupovniProizvod }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(KupovniProizvodDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.kupovniProizvod = kupovniProizvod;
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
