import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOstaliMaterijali } from 'app/shared/model/ostali-materijali.model';
import { OstaliMaterijaliService } from './ostali-materijali.service';

@Component({
    selector: 'jhi-ostali-materijali-delete-dialog',
    templateUrl: './ostali-materijali-delete-dialog.component.html'
})
export class OstaliMaterijaliDeleteDialogComponent {
    ostaliMaterijali: IOstaliMaterijali;

    constructor(
        private ostaliMaterijaliService: OstaliMaterijaliService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.ostaliMaterijaliService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'ostaliMaterijaliListModification',
                content: 'Deleted an ostaliMaterijali'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ostali-materijali-delete-popup',
    template: ''
})
export class OstaliMaterijaliDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ostaliMaterijali }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(OstaliMaterijaliDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.ostaliMaterijali = ostaliMaterijali;
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
