import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOperacije } from 'app/shared/model/operacije.model';
import { OperacijeService } from './operacije.service';

@Component({
    selector: 'jhi-operacije-delete-dialog',
    templateUrl: './operacije-delete-dialog.component.html'
})
export class OperacijeDeleteDialogComponent {
    operacije: IOperacije;

    constructor(private operacijeService: OperacijeService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.operacijeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'operacijeListModification',
                content: 'Deleted an operacije'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-operacije-delete-popup',
    template: ''
})
export class OperacijeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ operacije }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(OperacijeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.operacije = operacije;
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
