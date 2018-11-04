import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IKomponenete } from 'app/shared/model/komponenete.model';
import { KomponeneteService } from './komponenete.service';

@Component({
    selector: 'jhi-komponenete-delete-dialog',
    templateUrl: './komponenete-delete-dialog.component.html'
})
export class KomponeneteDeleteDialogComponent {
    komponenete: IKomponenete;

    constructor(
        private komponeneteService: KomponeneteService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.komponeneteService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'komponeneteListModification',
                content: 'Deleted an komponenete'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-komponenete-delete-popup',
    template: ''
})
export class KomponeneteDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ komponenete }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(KomponeneteDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.komponenete = komponenete;
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
