import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOdelenje } from 'app/shared/model/odelenje.model';
import { OdelenjeService } from './odelenje.service';

@Component({
    selector: 'jhi-odelenje-delete-dialog',
    templateUrl: './odelenje-delete-dialog.component.html'
})
export class OdelenjeDeleteDialogComponent {
    odelenje: IOdelenje;

    constructor(private odelenjeService: OdelenjeService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.odelenjeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'odelenjeListModification',
                content: 'Deleted an odelenje'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-odelenje-delete-popup',
    template: ''
})
export class OdelenjeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ odelenje }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(OdelenjeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.odelenje = odelenje;
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
