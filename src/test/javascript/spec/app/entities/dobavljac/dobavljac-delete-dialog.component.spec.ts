/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ManProdTestModule } from '../../../test.module';
import { DobavljacDeleteDialogComponent } from 'app/entities/dobavljac/dobavljac-delete-dialog.component';
import { DobavljacService } from 'app/entities/dobavljac/dobavljac.service';

describe('Component Tests', () => {
    describe('Dobavljac Management Delete Component', () => {
        let comp: DobavljacDeleteDialogComponent;
        let fixture: ComponentFixture<DobavljacDeleteDialogComponent>;
        let service: DobavljacService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [DobavljacDeleteDialogComponent]
            })
                .overrideTemplate(DobavljacDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DobavljacDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DobavljacService);
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
