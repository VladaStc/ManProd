import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IObradniSistem } from 'app/shared/model/obradni-sistem.model';
import { ObradniSistemService } from './obradni-sistem.service';

@Component({
    selector: 'jhi-obradni-sistem-delete-dialog',
    templateUrl: './obradni-sistem-delete-dialog.component.html'
})
export class ObradniSistemDeleteDialogComponent {
    obradniSistem: IObradniSistem;

    constructor(
        private obradniSistemService: ObradniSistemService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.obradniSistemService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'obradniSistemListModification',
                content: 'Deleted an obradniSistem'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-obradni-sistem-delete-popup',
    template: ''
})
export class ObradniSistemDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ obradniSistem }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ObradniSistemDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.obradniSistem = obradniSistem;
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
