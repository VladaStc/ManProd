import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IZahvati } from 'app/shared/model/zahvati.model';
import { ZahvatiService } from './zahvati.service';

@Component({
    selector: 'jhi-zahvati-delete-dialog',
    templateUrl: './zahvati-delete-dialog.component.html'
})
export class ZahvatiDeleteDialogComponent {
    zahvati: IZahvati;

    constructor(private zahvatiService: ZahvatiService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.zahvatiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'zahvatiListModification',
                content: 'Deleted an zahvati'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-zahvati-delete-popup',
    template: ''
})
export class ZahvatiDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ zahvati }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ZahvatiDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.zahvati = zahvati;
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
