import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMerniAlati } from 'app/shared/model/merni-alati.model';
import { MerniAlatiService } from './merni-alati.service';

@Component({
    selector: 'jhi-merni-alati-delete-dialog',
    templateUrl: './merni-alati-delete-dialog.component.html'
})
export class MerniAlatiDeleteDialogComponent {
    merniAlati: IMerniAlati;

    constructor(private merniAlatiService: MerniAlatiService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.merniAlatiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'merniAlatiListModification',
                content: 'Deleted an merniAlati'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-merni-alati-delete-popup',
    template: ''
})
export class MerniAlatiDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ merniAlati }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MerniAlatiDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.merniAlati = merniAlati;
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
