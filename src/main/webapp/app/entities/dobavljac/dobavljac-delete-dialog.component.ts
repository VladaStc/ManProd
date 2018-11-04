import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDobavljac } from 'app/shared/model/dobavljac.model';
import { DobavljacService } from './dobavljac.service';

@Component({
    selector: 'jhi-dobavljac-delete-dialog',
    templateUrl: './dobavljac-delete-dialog.component.html'
})
export class DobavljacDeleteDialogComponent {
    dobavljac: IDobavljac;

    constructor(private dobavljacService: DobavljacService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.dobavljacService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'dobavljacListModification',
                content: 'Deleted an dobavljac'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-dobavljac-delete-popup',
    template: ''
})
export class DobavljacDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dobavljac }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DobavljacDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.dobavljac = dobavljac;
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
