import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRadnici } from 'app/shared/model/radnici.model';
import { RadniciService } from './radnici.service';

@Component({
    selector: 'jhi-radnici-delete-dialog',
    templateUrl: './radnici-delete-dialog.component.html'
})
export class RadniciDeleteDialogComponent {
    radnici: IRadnici;

    constructor(private radniciService: RadniciService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.radniciService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'radniciListModification',
                content: 'Deleted an radnici'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-radnici-delete-popup',
    template: ''
})
export class RadniciDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ radnici }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RadniciDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.radnici = radnici;
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
