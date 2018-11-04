/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ManProdTestModule } from '../../../test.module';
import { ObradniSistemDeleteDialogComponent } from 'app/entities/obradni-sistem/obradni-sistem-delete-dialog.component';
import { ObradniSistemService } from 'app/entities/obradni-sistem/obradni-sistem.service';

describe('Component Tests', () => {
    describe('ObradniSistem Management Delete Component', () => {
        let comp: ObradniSistemDeleteDialogComponent;
        let fixture: ComponentFixture<ObradniSistemDeleteDialogComponent>;
        let service: ObradniSistemService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManProdTestModule],
                declarations: [ObradniSistemDeleteDialogComponent]
            })
                .overrideTemplate(ObradniSistemDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ObradniSistemDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ObradniSistemService);
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
