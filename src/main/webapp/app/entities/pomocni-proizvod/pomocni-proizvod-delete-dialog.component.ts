import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPomocniProizvod } from 'app/shared/model/pomocni-proizvod.model';
import { PomocniProizvodService } from './pomocni-proizvod.service';

@Component({
    selector: 'jhi-pomocni-proizvod-delete-dialog',
    templateUrl: './pomocni-proizvod-delete-dialog.component.html'
})
export class PomocniProizvodDeleteDialogComponent {
    pomocniProizvod: IPomocniProizvod;

    constructor(
        private pomocniProizvodService: PomocniProizvodService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.pomocniProizvodService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'pomocniProizvodListModification',
                content: 'Deleted an pomocniProizvod'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-pomocni-proizvod-delete-popup',
    template: ''
})
export class PomocniProizvodDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pomocniProizvod }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PomocniProizvodDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.pomocniProizvod = pomocniProizvod;
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
