/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ManProdTestModule } from '../../../test.module';
import { RadniciDeleteDialogComponent } from 'app/entities/radnici/radnici-delete-dialog.component';
import { RadniciService } from 'app/entities/radnici/radnici.service';

describe('Component Tests', () => {
    describe('Radnici Management Delete Component', () => {
        let comp: RadniciDeleteDialogComponent;
        let fixture: ComponentFixture<RadniciDeleteDialogComponent>;
        let service: RadniciService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [RadniciDeleteDialogComponent]
            })
                .overrideTemplate(RadniciDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RadniciDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RadniciService);
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
