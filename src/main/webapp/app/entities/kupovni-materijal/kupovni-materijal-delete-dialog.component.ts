import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IKupovniMaterijal } from 'app/shared/model/kupovni-materijal.model';
import { KupovniMaterijalService } from './kupovni-materijal.service';

@Component({
    selector: 'jhi-kupovni-materijal-delete-dialog',
    templateUrl: './kupovni-materijal-delete-dialog.component.html'
})
export class KupovniMaterijalDeleteDialogComponent {
    kupovniMaterijal: IKupovniMaterijal;

    constructor(
        private kupovniMaterijalService: KupovniMaterijalService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.kupovniMaterijalService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'kupovniMaterijalListModification',
                content: 'Deleted an kupovniMaterijal'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-kupovni-materijal-delete-popup',
    template: ''
})
export class KupovniMaterijalDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ kupovniMaterijal }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(KupovniMaterijalDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.kupovniMaterijal = kupovniMaterijal;
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
