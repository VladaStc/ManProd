import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISirovine } from 'app/shared/model/sirovine.model';
import { SirovineService } from './sirovine.service';

@Component({
    selector: 'jhi-sirovine-delete-dialog',
    templateUrl: './sirovine-delete-dialog.component.html'
})
export class SirovineDeleteDialogComponent {
    sirovine: ISirovine;

    constructor(private sirovineService: SirovineService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sirovineService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sirovineListModification',
                content: 'Deleted an sirovine'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sirovine-delete-popup',
    template: ''
})
export class SirovineDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sirovine }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SirovineDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sirovine = sirovine;
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
