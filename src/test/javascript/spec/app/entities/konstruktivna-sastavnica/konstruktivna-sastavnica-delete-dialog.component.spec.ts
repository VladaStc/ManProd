/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ManProdTestModule } from '../../../test.module';
import { KonstruktivnaSastavnicaDeleteDialogComponent } from 'app/entities/konstruktivna-sastavnica/konstruktivna-sastavnica-delete-dialog.component';
import { KonstruktivnaSastavnicaService } from 'app/entities/konstruktivna-sastavnica/konstruktivna-sastavnica.service';

describe('Component Tests', () => {
    describe('KonstruktivnaSastavnica Management Delete Component', () => {
        let comp: KonstruktivnaSastavnicaDeleteDialogComponent;
        let fixture: ComponentFixture<KonstruktivnaSastavnicaDeleteDialogComponent>;
        let service: KonstruktivnaSastavnicaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [KonstruktivnaSastavnicaDeleteDialogComponent]
            })
                .overrideTemplate(KonstruktivnaSastavnicaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KonstruktivnaSastavnicaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KonstruktivnaSastavnicaService);
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
