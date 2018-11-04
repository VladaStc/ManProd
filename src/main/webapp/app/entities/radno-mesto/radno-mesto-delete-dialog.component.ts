import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRadnoMesto } from 'app/shared/model/radno-mesto.model';
import { RadnoMestoService } from './radno-mesto.service';

@Component({
    selector: 'jhi-radno-mesto-delete-dialog',
    templateUrl: './radno-mesto-delete-dialog.component.html'
})
export class RadnoMestoDeleteDialogComponent {
    radnoMesto: IRadnoMesto;

    constructor(private radnoMestoService: RadnoMestoService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.radnoMestoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'radnoMestoListModification',
                content: 'Deleted an radnoMesto'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-radno-mesto-delete-popup',
    template: ''
})
export class RadnoMestoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ radnoMesto }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RadnoMestoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.radnoMesto = radnoMesto;
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
