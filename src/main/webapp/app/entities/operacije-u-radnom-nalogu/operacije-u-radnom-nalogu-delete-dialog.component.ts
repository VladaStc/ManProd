import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOperacijeURadnomNalogu } from 'app/shared/model/operacije-u-radnom-nalogu.model';
import { OperacijeURadnomNaloguService } from './operacije-u-radnom-nalogu.service';

@Component({
    selector: 'jhi-operacije-u-radnom-nalogu-delete-dialog',
    templateUrl: './operacije-u-radnom-nalogu-delete-dialog.component.html'
})
export class OperacijeURadnomNaloguDeleteDialogComponent {
    operacijeURadnomNalogu: IOperacijeURadnomNalogu;

    constructor(
        private operacijeURadnomNaloguService: OperacijeURadnomNaloguService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.operacijeURadnomNaloguService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'operacijeURadnomNaloguListModification',
                content: 'Deleted an operacijeURadnomNalogu'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-operacije-u-radnom-nalogu-delete-popup',
    template: ''
})
export class OperacijeURadnomNaloguDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ operacijeURadnomNalogu }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(OperacijeURadnomNaloguDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.operacijeURadnomNalogu = operacijeURadnomNalogu;
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
