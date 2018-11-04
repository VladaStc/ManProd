import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRadniNalog } from 'app/shared/model/radni-nalog.model';
import { RadniNalogService } from './radni-nalog.service';

@Component({
    selector: 'jhi-radni-nalog-delete-dialog',
    templateUrl: './radni-nalog-delete-dialog.component.html'
})
export class RadniNalogDeleteDialogComponent {
    radniNalog: IRadniNalog;

    constructor(private radniNalogService: RadniNalogService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.radniNalogService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'radniNalogListModification',
                content: 'Deleted an radniNalog'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-radni-nalog-delete-popup',
    template: ''
})
export class RadniNalogDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ radniNalog }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RadniNalogDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.radniNalog = radniNalog;
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
