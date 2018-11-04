import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITransakcijeUMagacinu } from 'app/shared/model/transakcije-u-magacinu.model';
import { TransakcijeUMagacinuService } from './transakcije-u-magacinu.service';

@Component({
    selector: 'jhi-transakcije-u-magacinu-delete-dialog',
    templateUrl: './transakcije-u-magacinu-delete-dialog.component.html'
})
export class TransakcijeUMagacinuDeleteDialogComponent {
    transakcijeUMagacinu: ITransakcijeUMagacinu;

    constructor(
        private transakcijeUMagacinuService: TransakcijeUMagacinuService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.transakcijeUMagacinuService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'transakcijeUMagacinuListModification',
                content: 'Deleted an transakcijeUMagacinu'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-transakcije-u-magacinu-delete-popup',
    template: ''
})
export class TransakcijeUMagacinuDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ transakcijeUMagacinu }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TransakcijeUMagacinuDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.transakcijeUMagacinu = transakcijeUMagacinu;
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
