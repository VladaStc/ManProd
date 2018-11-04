/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ManProdTestModule } from '../../../test.module';
import { PomocniProizvodDeleteDialogComponent } from 'app/entities/pomocni-proizvod/pomocni-proizvod-delete-dialog.component';
import { PomocniProizvodService } from 'app/entities/pomocni-proizvod/pomocni-proizvod.service';

describe('Component Tests', () => {
    describe('PomocniProizvod Management Delete Component', () => {
        let comp: PomocniProizvodDeleteDialogComponent;
        let fixture: ComponentFixture<PomocniProizvodDeleteDialogComponent>;
        let service: PomocniProizvodService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [PomocniProizvodDeleteDialogComponent]
            })
                .overrideTemplate(PomocniProizvodDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PomocniProizvodDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PomocniProizvodService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
