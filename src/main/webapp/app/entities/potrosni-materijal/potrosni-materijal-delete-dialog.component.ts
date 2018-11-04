import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPotrosniMaterijal } from 'app/shared/model/potrosni-materijal.model';
import { PotrosniMaterijalService } from './potrosni-materijal.service';

@Component({
    selector: 'jhi-potrosni-materijal-delete-dialog',
    templateUrl: './potrosni-materijal-delete-dialog.component.html'
})
export class PotrosniMaterijalDeleteDialogComponent {
    potrosniMaterijal: IPotrosniMaterijal;

    constructor(
        private potrosniMaterijalService: PotrosniMaterijalService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.potrosniMaterijalService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'potrosniMaterijalListModification',
                content: 'Deleted an potrosniMaterijal'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-potrosni-materijal-delete-popup',
    template: ''
})
export class PotrosniMaterijalDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ potrosniMaterijal }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PotrosniMaterijalDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.potrosniMaterijal = potrosniMaterijal;
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
