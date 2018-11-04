import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProizvodneLinije } from 'app/shared/model/proizvodne-linije.model';
import { ProizvodneLinijeService } from './proizvodne-linije.service';

@Component({
    selector: 'jhi-proizvodne-linije-delete-dialog',
    templateUrl: './proizvodne-linije-delete-dialog.component.html'
})
export class ProizvodneLinijeDeleteDialogComponent {
    proizvodneLinije: IProizvodneLinije;

    constructor(
        private proizvodneLinijeService: ProizvodneLinijeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.proizvodneLinijeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'proizvodneLinijeListModification',
                content: 'Deleted an proizvodneLinije'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-proizvodne-linije-delete-popup',
    template: ''
})
export class ProizvodneLinijeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ proizvodneLinije }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProizvodneLinijeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.proizvodneLinije = proizvodneLinije;
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
