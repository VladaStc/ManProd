/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ManProdTestModule } from '../../../test.module';
import { PoluproizvodDeleteDialogComponent } from 'app/entities/poluproizvod/poluproizvod-delete-dialog.component';
import { PoluproizvodService } from 'app/entities/poluproizvod/poluproizvod.service';

describe('Component Tests', () => {
    describe('Poluproizvod Management Delete Component', () => {
        let comp: PoluproizvodDeleteDialogComponent;
        let fixture: ComponentFixture<PoluproizvodDeleteDialogComponent>;
        let service: PoluproizvodService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [PoluproizvodDeleteDialogComponent]
            })
                .overrideTemplate(PoluproizvodDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PoluproizvodDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PoluproizvodService);
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
