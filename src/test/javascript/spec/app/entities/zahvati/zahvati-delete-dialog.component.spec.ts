/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ManProdTestModule } from '../../../test.module';
import { ZahvatiDeleteDialogComponent } from 'app/entities/zahvati/zahvati-delete-dialog.component';
import { ZahvatiService } from 'app/entities/zahvati/zahvati.service';

describe('Component Tests', () => {
    describe('Zahvati Management Delete Component', () => {
        let comp: ZahvatiDeleteDialogComponent;
        let fixture: ComponentFixture<ZahvatiDeleteDialogComponent>;
        let service: ZahvatiService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [ZahvatiDeleteDialogComponent]
            })
                .overrideTemplate(ZahvatiDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ZahvatiDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ZahvatiService);
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
