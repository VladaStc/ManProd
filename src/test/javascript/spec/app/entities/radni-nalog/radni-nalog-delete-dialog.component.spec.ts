/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ManProdTestModule } from '../../../test.module';
import { RadniNalogDeleteDialogComponent } from 'app/entities/radni-nalog/radni-nalog-delete-dialog.component';
import { RadniNalogService } from 'app/entities/radni-nalog/radni-nalog.service';

describe('Component Tests', () => {
    describe('RadniNalog Management Delete Component', () => {
        let comp: RadniNalogDeleteDialogComponent;
        let fixture: ComponentFixture<RadniNalogDeleteDialogComponent>;
        let service: RadniNalogService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [RadniNalogDeleteDialogComponent]
            })
                .overrideTemplate(RadniNalogDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RadniNalogDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RadniNalogService);
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
