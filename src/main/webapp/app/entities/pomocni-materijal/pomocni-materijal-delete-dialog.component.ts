import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPomocniMaterijal } from 'app/shared/model/pomocni-materijal.model';
import { PomocniMaterijalService } from './pomocni-materijal.service';

@Component({
    selector: 'jhi-pomocni-materijal-delete-dialog',
    templateUrl: './pomocni-materijal-delete-dialog.component.html'
})
export class PomocniMaterijalDeleteDialogComponent {
    pomocniMaterijal: IPomocniMaterijal;

    constructor(
        private pomocniMaterijalService: PomocniMaterijalService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.pomocniMaterijalService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'pomocniMaterijalListModification',
                content: 'Deleted an pomocniMaterijal'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-pomocni-materijal-delete-popup',
    template: ''
})
export class PomocniMaterijalDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pomocniMaterijal }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PomocniMaterijalDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.pomocniMaterijal = pomocniMaterijal;
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
