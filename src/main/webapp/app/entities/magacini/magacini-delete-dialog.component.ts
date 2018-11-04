import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMagacini } from 'app/shared/model/magacini.model';
import { MagaciniService } from './magacini.service';

@Component({
    selector: 'jhi-magacini-delete-dialog',
    templateUrl: './magacini-delete-dialog.component.html'
})
export class MagaciniDeleteDialogComponent {
    magacini: IMagacini;

    constructor(private magaciniService: MagaciniService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.magaciniService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'magaciniListModification',
                content: 'Deleted an magacini'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-magacini-delete-popup',
    template: ''
})
export class MagaciniDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ magacini }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MagaciniDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.magacini = magacini;
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
