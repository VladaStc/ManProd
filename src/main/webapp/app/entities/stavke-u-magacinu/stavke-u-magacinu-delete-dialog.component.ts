import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStavkeUMagacinu } from 'app/shared/model/stavke-u-magacinu.model';
import { StavkeUMagacinuService } from './stavke-u-magacinu.service';

@Component({
    selector: 'jhi-stavke-u-magacinu-delete-dialog',
    templateUrl: './stavke-u-magacinu-delete-dialog.component.html'
})
export class StavkeUMagacinuDeleteDialogComponent {
    stavkeUMagacinu: IStavkeUMagacinu;

    constructor(
        private stavkeUMagacinuService: StavkeUMagacinuService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.stavkeUMagacinuService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'stavkeUMagacinuListModification',
                content: 'Deleted an stavkeUMagacinu'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-stavke-u-magacinu-delete-popup',
    template: ''
})
export class StavkeUMagacinuDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ stavkeUMagacinu }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(StavkeUMagacinuDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.stavkeUMagacinu = stavkeUMagacinu;
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
