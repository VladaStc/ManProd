/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ManProdTestModule } from '../../../test.module';
import { OdelenjeDeleteDialogComponent } from 'app/entities/odelenje/odelenje-delete-dialog.component';
import { OdelenjeService } from 'app/entities/odelenje/odelenje.service';

describe('Component Tests', () => {
    describe('Odelenje Management Delete Component', () => {
        let comp: OdelenjeDeleteDialogComponent;
        let fixture: ComponentFixture<OdelenjeDeleteDialogComponent>;
        let service: OdelenjeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [OdelenjeDeleteDialogComponent]
            })
                .overrideTemplate(OdelenjeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OdelenjeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OdelenjeService);
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
